package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.entities.Cliente;
import au.com.xpto.gvendas.gestaovendas.exceptions.RegraNegocioException;
import au.com.xpto.gvendas.gestaovendas.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClientRepository clientRepository;

    public ClienteService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Cliente> listarTodos(){
        return this.clientRepository.findAll();
    }

    public Optional<Cliente> buscarPorCodigo(Long codigo){
        return this.clientRepository.findById(codigo);
    }

    public Cliente salvar(Cliente cliente){
        this.validarClienteDuplicado(cliente);
        return this.clientRepository.save(cliente);
    }

    public Cliente atualizar(Long codigo, Cliente cliente){
        Cliente clienteAtualizar = this.validarClienteExiste(codigo);
        this.validarClienteDuplicado(cliente);
        BeanUtils.copyProperties(cliente, clienteAtualizar, "codigo");
        return this.clientRepository.save(clienteAtualizar);
    }

    public void deletar(Long codigo){
        this.clientRepository.deleteById(codigo);
    }

    private Cliente validarClienteExiste(Long codigo){
        Optional<Cliente> optionalCliente = this.buscarPorCodigo(codigo);
        if(!optionalCliente.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return optionalCliente.get();
    }

    private void validarClienteDuplicado(Cliente cliente){
        Cliente clientePorNome = this.clientRepository.findByNome(cliente.getNome());
        if(clientePorNome != null && clientePorNome.getCodigo() != cliente.getCodigo()){
            throw new RegraNegocioException(String.format("Cliente %s ja cadastrado", cliente.getNome().toUpperCase()));
        }
    }

}
