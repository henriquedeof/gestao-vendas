package au.com.xpto.gvendas.gestaovendas.repositories;

import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaCodigo(Long codigoCategoria);

    @Query("SELECT prod from Produto prod where prod.codigo = :codigo and prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);
    //For the query above, I believe I could have the same results if I use the following criteria 'findByCodigoAndCategoriaCodigo'

    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);

}
