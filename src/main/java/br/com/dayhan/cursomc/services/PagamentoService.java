package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Pagamento;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PagamentoService
 */
@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento find(Integer id) {
        return this.pagamentoRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + Pagamento.class.getSimpleName()));
    }

    public Pagamento insert(Pagamento pagamento) {
        pagamento.setId(null);
        return this.pagamentoRepository.save(pagamento);
    }
}