package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.entities.Cliente;
import au.com.xpto.gvendas.gestaovendas.entities.ItemVenda;
import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import au.com.xpto.gvendas.gestaovendas.entities.Venda;
import au.com.xpto.gvendas.gestaovendas.exceptions.RegraNegocioException;
import au.com.xpto.gvendas.gestaovendas.repositories.ItemVendaRepository;
import au.com.xpto.gvendas.gestaovendas.repositories.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService extends AbstractVendaService{

    private final ClienteService clienteService;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoService produtoService;

    public VendaService(ClienteService clienteService, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.clienteService = clienteService;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }


    public ClienteVendaResponseDTO listarVendasPorCliente(Long codigoCliente){
        Cliente cliente = this.validarClienteVendaExiste(codigoCliente);
        List<VendaResponseDTO> vendaResponseDTOS = this.vendaRepository.findByClienteCodigo(codigoCliente)
                .stream()
                .map(venda -> criandoVendaResponseDTO(venda, this.itemVendaRepository.findByVendaPorCodigo(venda.getCodigo())))
                .collect(Collectors.toList());

        return new ClienteVendaResponseDTO(cliente.getNome(), vendaResponseDTOS);
    }

    public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda){
        Venda venda = this.validarVendaExiste(codigoVenda);
        return this.retornandoClienteVendaResponseDTO(venda, this.itemVendaRepository.findByVendaPorCodigo(venda.getCodigo()));
    }

    //Using @Transactional to ensure that if any exception is thrown, the rollback will be executed and no table will be updated.
    //Maybe just the @Transactional annotation without the params would be enough.
    //This annotation is extremely important when involving changes in more than just one tables.
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class) //readOnly = false and  Propagation.REQUIRED are the default values
    public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO vendaDTO){
        Cliente cliente = this.validarClienteVendaExiste(codigoCliente);
        validarProdutoExisteEAtualizarQuantidade(vendaDTO.getItensVendaDTO());

        Venda vendaSalva = this.salvarVenda(cliente, vendaDTO);

        return this.retornandoClienteVendaResponseDTO(vendaSalva, this.itemVendaRepository.findByVendaPorCodigo(vendaSalva.getCodigo()));
    }

    @Transactional
    public void deletar(Long codigoVenda){
        this.validarVendaExiste(codigoVenda);
        List<ItemVenda> itensVenda = this.itemVendaRepository.findByVendaPorCodigo(codigoVenda);

        validarProdutoExisteEDevolverEstoque(itensVenda);
        itemVendaRepository.deleteAll(itensVenda);
        vendaRepository.deleteById(codigoVenda);
    }

    @Transactional
    public ClienteVendaResponseDTO atualizar(Long codigoVenda, Long codigoCliente, VendaRequestDTO vendaRequestDTO){
        validarVendaExiste(codigoVenda);
        Cliente cliente = validarClienteVendaExiste(codigoCliente);
        List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaPorCodigo(codigoVenda);
        validarProdutoExisteEDevolverEstoque(itensVendaList);
        validarProdutoExisteEAtualizarQuantidade(vendaRequestDTO.getItensVendaDTO());
        itemVendaRepository.deleteAll(itensVendaList);
        Venda vendaAtualizada = atualizarVenda(codigoVenda, cliente, vendaRequestDTO);
        return retornandoClienteVendaResponseDTO(vendaAtualizada, itemVendaRepository.findByVendaPorCodigo(vendaAtualizada.getCodigo()));
    }

    //-------------------

    private void validarProdutoExisteEDevolverEstoque(List<ItemVenda> itensVenda){
        itensVenda.forEach(itemVenda -> {
            Produto produto = produtoService.validarProdutoExiste(itemVenda.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + itemVenda.getQuantidade());
            produtoService.atualizarQuantidadeEmEstoque(produto);
        });
    }

    private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaRequestDTO){
        Venda vendaSalva = this.vendaRepository.save(new Venda(vendaRequestDTO.getData(), cliente));
        vendaRequestDTO.getItensVendaDTO()
                .stream()
                .map(itemVendaDTO -> criandoItemVenda(itemVendaDTO, vendaSalva))
                .forEach(this.itemVendaRepository::save);

        return vendaSalva;
    }

    private Venda atualizarVenda(Long codigoVenda, Cliente cliente, VendaRequestDTO vendaRequestDTO){
        Venda vendaSalva = this.vendaRepository.save(new Venda(codigoVenda, vendaRequestDTO.getData(), cliente));
        vendaRequestDTO.getItensVendaDTO()
                .stream()
                .map(itemVendaDTO -> criandoItemVenda(itemVendaDTO, vendaSalva))
                .forEach(this.itemVendaRepository::save);

        return vendaSalva;
    }

    //Maybe I have to change this method, because If I have 3 products and one does not have enough in stock the other 2 will be subtracted
    private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itensVendaDTO) {
        itensVendaDTO.forEach(item -> {
            Produto produto = this.produtoService.validarProdutoExiste(item.getCodigoProduto());
            validarQuantidadeProdutoExiste(produto, item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoService.atualizarQuantidadeEmEstoque(produto);
        });
    }

    private void validarQuantidadeProdutoExiste(Produto produto, Integer qtdVendaDto){
        if(!(produto.getQuantidade() >= qtdVendaDto)){
            throw new RegraNegocioException(String.format("A quantidade %s informada para o produto %s nao esta disponivel em estoque",
                    qtdVendaDto, produto.getDescricao()));
        }
    }

    private Venda validarVendaExiste(Long codigoVenda){
        Optional<Venda> venda = this.vendaRepository.findById(codigoVenda);
        if(!venda.isPresent()){
            throw new RegraNegocioException(String.format("Venda com codigo %s nao encontrado", codigoVenda));
        }
        return venda.get();
    }

    private Cliente validarClienteVendaExiste(Long codigoCliente){
        Optional<Cliente> clienteOptional = this.clienteService.buscarPorCodigo(codigoCliente);
        if(!clienteOptional.isPresent()){
            throw new RegraNegocioException(String.format("O cliente de codigo %s nao existe no cadastro.", codigoCliente));
        }
        return clienteOptional.get();
    }

}
