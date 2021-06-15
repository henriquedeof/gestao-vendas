package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.ItemVendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.entities.ItemVenda;
import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import au.com.xpto.gvendas.gestaovendas.entities.Venda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractVendaService {

    protected VendaResponseDTO criandoVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList){
        List<ItemVendaResponseDTO> listaItemVendaResponseDto = itensVendaList
                .stream()
                .map(this::criandoItemVendaResponseDTO)
                .collect(Collectors.toList());

        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), listaItemVendaResponseDto);
    }

    protected ItemVendaResponseDTO criandoItemVendaResponseDTO(ItemVenda itemVenda){
        return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
                itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
    }

    protected ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda, List<ItemVenda> itemVendaList){
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
                Arrays.asList(this.criandoVendaResponseDTO(venda, itemVendaList)));
    }

    protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaDTO, Venda venda){
        return new ItemVenda(itemVendaDTO.getQuantidade(), itemVendaDTO.getPrecoVendido(), new Produto(itemVendaDTO.getCodigoProduto()), venda);
    }

}
