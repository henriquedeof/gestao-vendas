package au.com.xpto.gvendas.gestaovendas.controllers;

import au.com.xpto.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import au.com.xpto.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
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
import java.util.stream.Collectors;

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
    public List<CategoriaResponseDTO> listarTodas(){
        return this.categoriaService.listarTodas().stream()
                .map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar categorias por codigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorCodigo(@PathVariable Long codigo){
        Optional<Categoria> categoria = this.categoriaService.buscarPorCodigo(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        //Annotation @Valid validates if the attributes of categoria object are valid, according to the validation annotations.
        //Link how to use @Validate: https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4

        Categoria categoriaSalva = this.categoriaService.salvar(categoriaRequestDTO.converterParaEntidade());
        return new ResponseEntity<>(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva), HttpStatus.CREATED);
        //return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.salvar(categoria)); //Generates the same result as above.
    }

    @ApiOperation(value = "Atualizar categoria")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        Categoria categoriaAtualizada = this.categoriaService.atualizar(codigo, categoriaRequestDTO.converterParaEntidade(codigo));
        return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada));
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
