package au.com.xpto.gvendas.gestaovendas.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao;
    private Integer quantidade;

    @Column(name = "preco_curto")
    private BigDecimal precoCusto; // I believe the instructor mapped this field by mistake as preco_curto. It should be preco_custo
    private BigDecimal precoVenda;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
    private Categoria categoria;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (codigo != product.codigo) return false;
        if (descricao != null ? !descricao.equals(product.descricao) : product.descricao != null) return false;
        if (quantidade != null ? !quantidade.equals(product.quantidade) : product.quantidade != null) return false;
        if (precoCusto != null ? !precoCusto.equals(product.precoCusto) : product.precoCusto != null) return false;
        if (precoVenda != null ? !precoVenda.equals(product.precoVenda) : product.precoVenda != null) return false;
        if (observacao != null ? !observacao.equals(product.observacao) : product.observacao != null) return false;
        return categoria != null ? categoria.equals(product.categoria) : product.categoria == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (codigo ^ (codigo >>> 32));
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (precoCusto != null ? precoCusto.hashCode() : 0);
        result = 31 * result + (precoVenda != null ? precoVenda.hashCode() : 0);
        result = 31 * result + (observacao != null ? observacao.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }
}
