package au.com.xpto.gvendas.gestaovendas.dto.cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Endereco request DTO")
public class EnderecoRequestDTO {

    @ApiModelProperty(value = "Logradouro")
    @NotBlank(message = "Logradouro eh obrigatorio")
    @Length(min = 3, max = 50, message = "Logradouro deve ter entre 3 e 50 caracteres")
    private String logradouro;

    @ApiModelProperty(value = "Numero")
    @NotNull(message = "Numero eh obrigatorio")
    private Integer numero;

    @ApiModelProperty(value = "Complemento")
    @Length(max = 30, message = "Complemento deve ter entre 3 e 50 caracteres")
    @NotBlank(message = "Complemento eh obrigatorio")
    private String complemento;

    @ApiModelProperty(value = "Bairro")
    @Length(min = 3, max = 30, message = "Bairro deve ter entre 3 e 50 caracteres")
    @NotBlank(message = "Bairro eh obrigatorio")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    @Pattern(regexp = "[\\d]{5}[-][\\d]{3}", message = "Cep com formato invalido")
    @NotBlank(message = "Cep eh obrigatorio")
    private String cep;

    @ApiModelProperty(value = "Cidade")
    @Length(min = 3, max = 30, message = "Cidade deve ter entre 3 e 50 caracteres")
    @NotBlank(message = "Cidade eh obrigatorio")
    private String cidade;

    @ApiModelProperty(value = "Estado")
    @Length(min = 3, max = 30, message = "Estado deve ter entre 3 e 50 caracteres")
    @NotBlank(message = "Estado eh obrigatorio")
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
