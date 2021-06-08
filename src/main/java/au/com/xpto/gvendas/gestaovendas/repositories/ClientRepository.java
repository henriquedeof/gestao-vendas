package au.com.xpto.gvendas.gestaovendas.repositories;

import au.com.xpto.gvendas.gestaovendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);

}
