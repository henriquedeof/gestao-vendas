package au.com.xpto.gvendas.gestaovendas.repositories;

import au.com.xpto.gvendas.gestaovendas.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    //Accessing in Venda the Cliente and then inside of Cliente I am accessing its codigo attribute. Venda >> Cliente >> codigo
    List<Venda> findByClienteCodigo(Long codigoCliente);

}
