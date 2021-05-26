package au.com.xpto.gvendas.gestaovendas.controllers;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import au.com.xpto.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria") //This path should be put in plural (not singular).
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @ApiOperation(value = "Listar categorias")
    @GetMapping
    public List<Categoria> listarTodas(){
        return this.categoriaService.listarTodas();
    }

    @ApiOperation(value = "Listar categorias por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorCodigo(@PathVariable Long codigo){
        Optional<Categoria> categoria = this.categoriaService.buscarPorCodigo(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar categoria")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        //Annotation @Valid validates if the attributes of categoria object are valid, according to the validation annotations.

        return new ResponseEntity<>(this.categoriaService.salvar(categoria), HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.salvar(categoria)); //Generates the same result as above.
    }

    @ApiOperation(value = "Atualizar categoria")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
        return ResponseEntity.ok(this.categoriaService.atualizar(codigo, categoria));
    }

    @ApiOperation(value = "Deletar categoria")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){

        this.categoriaService.delete(codigo);
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT); I can use this way or above and the result is the same.
        //NOTE: If I use @ResponseStatus(HttpStatus.OK) and ResponseEntity<>(HttpStatus.NO_CONTENT), my response is NO_CONTENT.

        //Douglas example below.
//        return this.lancamentoService.lancamentoPorId(id).map(lancamento -> {
//            this.lancamentoService.deletarLancamento(lancamento);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }).orElseGet(() -> new ResponseEntity("Lancamento nao encontrado na base de dados.", HttpStatus.BAD_REQUEST));//Poderia ser NOT_FOUND

    }


}
