package au.com.xpto.gvendas.gestaovendas.dto.categoria;

import au.com.xpto.gvendas.gestaovendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categoria Response DTO")
public class CategoriaResponseDTO {

    @ApiModelProperty(value = "Codigo")//On Swagger-site-client, the 'Models' tab will display 'Codigo' when it is rendered on the screen.
    private Long codigo;

    @ApiModelProperty(value = "Nome")
    private String nome;

    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
