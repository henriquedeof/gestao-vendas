package au.com.xpto.gvendas.gestaovendas.repositories;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
