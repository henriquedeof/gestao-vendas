package au.com.xpto.gvendas.gestaovendas.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
    private Cliente cliente;

    public Venda() {    }

    public Venda(Long codigo, LocalDate data, Cliente cliente) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
    }

    public Venda(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venda venda = (Venda) o;

        if (codigo != null ? !codigo.equals(venda.codigo) : venda.codigo != null) return false;
        if (data != null ? !data.equals(venda.data) : venda.data != null) return false;
        return cliente != null ? cliente.equals(venda.cliente) : venda.cliente == null;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (cliente != null ? cliente.hashCode() : 0);
        return result;
    }
}
