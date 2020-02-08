package br.com.dayhan.cursomc.repositories;

import br.com.dayhan.cursomc.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PagamentoRepository
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}