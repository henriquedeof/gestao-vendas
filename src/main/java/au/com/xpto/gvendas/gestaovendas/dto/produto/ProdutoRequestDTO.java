package au.com.xpto.gvendas.gestaovendas.dto.produto;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import au.com.xpto.gvendas.gestaovendas.entities.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto Request DTO")
public class ProdutoRequestDTO {

    //Link how to use @Validate: https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4

    @ApiModelProperty(value = "Descricao")
    @NotBlank(message = "Descricao eh obrigatorio")
    @Length(min = 3, max = 100, message = "Descricao deve ter entre 3 e 50 caracteres")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade eh obrigatoria")
    private Integer quantidade;

    @NotNull(message = "Preco custo eh obrigatorio")
    @ApiModelProperty(value = "Preco custo")
    private BigDecimal precoCusto;

    @ApiModelProperty(value = "Preco venda")
    @NotNull(message = "Preco venda eh obrigatorio")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "Observacao")
    @Length(max = 500, message = "Observacao deve ter no maximo 500 caracteres")
    private String observacao;

    public Produto converterParaEntidade(Long codigoCategoria){
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public Produto converterParaEntidade(Long codigoCategoria, Long codigoProduto){
        return new Produto(codigoProduto, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
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
}
