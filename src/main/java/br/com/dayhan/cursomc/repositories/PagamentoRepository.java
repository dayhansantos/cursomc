package br.com.dayhan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dayhan.cursomc.domain.Pagamento;

/**
 * PagamentoRepository
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}