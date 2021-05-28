package au.com.xpto.gvendas.gestaovendas.controllers;

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
    public List<Produto> listarTodos(@PathVariable Long codigoCategoria){
        return this.produtoService.listarTodos(codigoCategoria);
    }

    @ApiOperation(value = "Listar produtos por codigo", nickname = "buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Produto>> buscarPorCodigo(@PathVariable Long codigoCategoria, @PathVariable Long codigo){
        Optional<Produto> produto = this.produtoService.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok().body(produto) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar produto", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto){
        Produto produtoSalvo = this.produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }


}
