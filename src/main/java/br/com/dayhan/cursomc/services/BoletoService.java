package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * BoletoService
 */
@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(final PagamentoComBoleto pagto, final Date instanteDoPedido) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(calendar.getTime());
    }

}