package au.com.xpto.gvendas.gestaovendas.dto.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Itens venda Retorno DTO")
public class ItemVendaResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preco Vendido")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "Codigo produto")
    private Long codigoProduto;

    @ApiModelProperty(value = "Descricao produto")
    private String produtoDescricao;

    public ItemVendaResponseDTO(Long codigo, Integer quantidade, BigDecimal precoVendido, Long codigoProduto, String produtoDescricao) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.codigoProduto = codigoProduto;
        this.produtoDescricao = produtoDescricao;
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

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }
}
