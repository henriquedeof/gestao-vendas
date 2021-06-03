package au.com.xpto.gvendas.gestaovendas.controllers;

import au.com.xpto.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import au.com.xpto.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto") //This path appears really bad formatted.
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    //Attribute 'nickname' will change the name of the method on the URL. Before using it, my URL was '/Produto/listarTodosUsingGET' and now '/Produto/listarTodos'
    @ApiOperation(value = "Listar produtos", nickname = "listarTodos")
    @GetMapping
    public List<ProdutoResponseDTO> listarTodos(@PathVariable Long codigoCategoria){
        return this.produtoService.listarTodos(codigoCategoria).stream()
                .map(ProdutoResponseDTO::converterParaProdutoDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar produtos por codigo", nickname = "buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo(@PathVariable Long codigoCategoria, @PathVariable Long codigo){
        Optional<Produto> produto = this.produtoService.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar produto", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO){
        //Link how to use @Validate: https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4
        Produto produtoSalvo = this.produtoService.salvar(codigoCategoria, produtoRequestDTO.converterParaEntidade(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
    }

    @ApiOperation(value = "Atualizar produto", nickname = "atualizarProduto")
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long codigoCategoria,
                                             @PathVariable Long codigoProduto, @Valid @RequestBody ProdutoRequestDTO produto){

        Produto produtoAtualizado = this.produtoService.atualizar(codigoCategoria, codigoProduto, produto.converterParaEntidade(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtoAtualizado));
    }

    @ApiOperation(value = "Deletar produto", nickname = "deletarProduto")
    @DeleteMapping("/{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto){
        this.produtoService.deletar(codigoCategoria, codigoProduto);
    }


}
