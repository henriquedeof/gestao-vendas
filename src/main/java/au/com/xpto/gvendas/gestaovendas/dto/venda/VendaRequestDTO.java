package au.com.xpto.gvendas.gestaovendas.dto.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Request DTO")
public class VendaRequestDTO {

    @ApiModelProperty(value = "Data")
    @NotNull(message = "Data eh obrigatoria")
    private LocalDate data;

    @ApiModelProperty(value = "Data")
    @NotNull(message = "Itens de venda sao obrigatorios")
    @Valid
    private List<ItemVendaRequestDTO> itensVendaDTO;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaRequestDTO> getItensVendaDTO() {
        return itensVendaDTO;
    }

    public void setItensVendaDTO(List<ItemVendaRequestDTO> itensVendaDTO) {
        this.itensVendaDTO = itensVendaDTO;
    }
}
