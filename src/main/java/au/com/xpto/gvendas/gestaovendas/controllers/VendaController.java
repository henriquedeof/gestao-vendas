package au.com.xpto.gvendas.gestaovendas.controllers;

import au.com.xpto.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import au.com.xpto.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import au.com.xpto.gvendas.gestaovendas.services.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda") //This path appears really bad formatted.
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @ApiOperation(value = "Listar vendas por cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(this.vendaService.listarVendasPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar venda por codigo", nickname = "listarVendaPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda){
        return ResponseEntity.ok(this.vendaService.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar venda", nickname = "registrarVenda")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long codigoCliente, @Valid @RequestBody VendaRequestDTO vendaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.vendaService.salvar(codigoCliente, vendaDTO));
    }

    @ApiOperation(value = "Deletar venda", nickname = "deletarVenda")
    @DeleteMapping("/{codigoVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigoVenda){
        this.vendaService.deletar(codigoVenda);
    }

    @ApiOperation(value = "Atualizar venda", nickname = "atualizarVenda")
    @PutMapping("/{codigoVenda}/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> atualizar(@PathVariable Long codigoVenda,
                                                             @PathVariable Long codigoCliente,
                                                             @Valid @RequestBody VendaRequestDTO vendaDTO){

        return ResponseEntity.ok(this.vendaService.atualizar(codigoVenda, codigoCliente, vendaDTO));
    }



}
