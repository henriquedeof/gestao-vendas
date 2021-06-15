package au.com.xpto.gvendas.gestaovendas.repositories;

import au.com.xpto.gvendas.gestaovendas.entities.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query("select new au.com.xpto.gvendas.gestaovendas.entities.ItemVenda(" + //I write without the path qualified au.com.xpto.gvendas.gestaovendas.entities.Class
            "iv.codigo, iv.quantidade, iv.precoVendido, iv.produto, iv.venda)" +
            " from ItemVenda iv" +
            " where iv.venda.codigo = :codigoVenda")
    List<ItemVenda> findByVendaPorCodigo(Long codigoVenda);

}
