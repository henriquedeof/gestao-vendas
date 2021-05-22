package au.com.xpto.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EntityScan(basePackages = {"au.com.xpto.gvendas.gestaovendas.entities"}) //Mapping the DB entities package.
//@EnableJpaRepositories(basePackages = {"au.com.xpto.gvendas.gestaovendas.repositories"}) //Mapping JPA repositories
//@ComponentScan(basePackages = {"au.com.xpto.gvendas.gestaovendas.controllers", "au.com.xpto.gvendas.gestaovendas.services"}) //Beans, Services and Controllers
@SpringBootApplication
public class GestaoVendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoVendasApplication.class, args);
    }

}
