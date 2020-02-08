package br.com.dayhan.cursomc.domain;

import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * PagamentoComBoleto
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

    private static final long serialVersionUID = 8820675495157014999L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    public PagamentoComBoleto(final Integer id, final EstadoPagamento estado, final Pedido pedido,
            final Date dataVencimento, final Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}