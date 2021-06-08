package au.com.xpto.gvendas.gestaovendas.controllers;

import au.com.xpto.gvendas.gestaovendas.dto.cliente.ClienteRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import au.com.xpto.gvendas.gestaovendas.entities.Cliente;
import au.com.xpto.gvendas.gestaovendas.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente") //According to the best practices, should be plural
public class ClienteControllador {

    private final ClienteService clienteService;

    public ClienteControllador(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Listar clientes", nickname = "listarTodosClientes")
    @GetMapping
    public List<ClienteResponseDTO> listarTodos(){
        return this.clienteService.listarTodos()
                .stream()
                .map(cliente -> ClienteResponseDTO.converterParaClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar clientes por codigo", nickname = "listarClientesPorId")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCodigo(@PathVariable Long codigo){
        Optional<Cliente> cliente = this.clienteService.buscarPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(cliente.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar cliente", nickname = "salvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteDTO){
        Cliente clienteSalvo = this.clienteService.salvar(clienteDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
    }

    @ApiOperation(value = "Atualizar cliente", nickname = "atualizarCliente")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteDTO){
        Cliente clienteAtualizado = this.clienteService.atualizar(codigo, clienteDTO.converterParaEntidade(codigo));
        return ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(clienteAtualizado));
    }

    @ApiOperation(value = "Deletar cliente", nickname = "deletarCliente")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long codigo){
        this.clienteService.deletar(codigo);
    }

}
