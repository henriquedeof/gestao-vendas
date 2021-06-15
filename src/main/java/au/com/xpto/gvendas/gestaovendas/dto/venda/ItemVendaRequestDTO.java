package au.com.xpto.gvendas.gestaovendas.dto.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Itens da venda Request DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Codigo produto")
    @NotNull(message = "Codigo do produto eh obrigatorio")
    private Long codigoProduto;

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade eh obrigatoria")
    @Min(value = 1, message = "Quantidade deve ter valor minimo de 1")
    private Integer quantidade;

    @ApiModelProperty(value = "Preco Vendido")
    @NotNull(message = "Preco vendido eh obrigatorio")
    private BigDecimal precoVendido;

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
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
}
