package au.com.xpto.gvendas.gestaovendas.dto.categoria;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel("Categoria Request DTO")
public class CategoriaRequestDTO {

    //Link how to use @Validate: https://medium.com/javarevisited/are-you-using-valid-and-validated-annotations-wrong-b4a35ac1bca4

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome eh obrigatorio")
    @Length(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    public Categoria converterParaEntidade(){
        return new Categoria(this.nome);
    }

    public Categoria converterParaEntidade(Long codigo){
        return new Categoria(codigo, this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
