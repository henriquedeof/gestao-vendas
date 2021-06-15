package au.com.xpto.gvendas.gestaovendas.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Integer quantidade;

    @Column(name = "preco_vendido")//If I do not have this annotation, what should be the default config for this column? I need to test it.
    private BigDecimal precoVendido;

    @ManyToOne
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(Long codigo, Integer quantidade, BigDecimal precoVendido, Produto produto, Venda venda) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.produto = produto;
        this.venda = venda;
    }

    public ItemVenda(Integer quantidade, BigDecimal precoVendido, Produto produto, Venda venda) {
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.produto = produto;
        this.venda = venda;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(BigDecimal precoVendido) {
        this.precoVendido = precoVendido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemVenda itemVenda = (ItemVenda) o;

        if (codigo != null ? !codigo.equals(itemVenda.codigo) : itemVenda.codigo != null) return false;
        if (quantidade != null ? !quantidade.equals(itemVenda.quantidade) : itemVenda.quantidade != null) return false;
        if (precoVendido != null ? !precoVendido.equals(itemVenda.precoVendido) : itemVenda.precoVendido != null)
            return false;
        if (produto != null ? !produto.equals(itemVenda.produto) : itemVenda.produto != null) return false;
        return venda != null ? venda.equals(itemVenda.venda) : itemVenda.venda == null;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (precoVendido != null ? precoVendido.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        result = 31 * result + (venda != null ? venda.hashCode() : 0);
        return result;
    }
}
