package au.com.xpto.gvendas.gestaovendas.services;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import au.com.xpto.gvendas.gestaovendas.repositories.CategoriaRepository;
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

    public Optional<Categoria> buscarPorId(Long codigo){
        return this.categoriaRepository.findById(codigo);
    }

}
