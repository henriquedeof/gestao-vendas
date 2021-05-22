package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import au.com.xpto.gvendas.gestaovendas.repositories.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    //I should have created an interface CategoriaService and this class should be CategoriaServiceImpl

    //@Autowired //Annotation not recommended as it is preferred having the injection through constructor.
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas(){
        return this.categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorCodigo(Long codigo){
        return this.categoriaRepository.findById(codigo);
    }

    public Categoria salvar(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria){
        Categoria categoriaSalvar = this.validarCategoriaExiste(codigo);
        BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");//Copy categoria's attributes into categoriaSalvar but ignore 'codigo' attribute.
        return this.categoriaRepository.save(categoriaSalvar);
    }

    private Categoria validarCategoriaExiste(Long codigo){
        Optional<Categoria> categoria = this.buscarPorCodigo(codigo);
        if(!categoria.isPresent()){
            throw new EmptyResultDataAccessException(1); //I can use this exception for empty results from the database, when I am expecting at least one result.
        }

        return categoria.get();
    }

}
