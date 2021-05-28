package au.com.xpto.gvendas.gestaovendas.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Nome eh obrigatorio")
    @Length(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (codigo != categoria.codigo) return false;
        return nome != null ? nome.equals(categoria.nome) : categoria.nome == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (codigo ^ (codigo >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}
