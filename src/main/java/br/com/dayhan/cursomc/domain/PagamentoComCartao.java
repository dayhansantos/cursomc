package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * PagamentoComCartao
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoComCartao extends Pagamento {

    private static final long serialVersionUID = -2171156490119149740L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao(final Integer id, final EstadoPagamento estado, final Pedido pedido,
            final Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}