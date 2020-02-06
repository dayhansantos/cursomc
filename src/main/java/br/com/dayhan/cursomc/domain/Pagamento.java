package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Pagamento
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "pedido")
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = -1064927646315614941L;
    @Id
    private Integer id;
    private Integer estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // Garante que o ID do pedido será o mesmo que o ID de pagamento
	@JsonIgnore
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = (estado == null) ? null : estado.getCod();
        this.pedido = pedido;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(this.estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }
}