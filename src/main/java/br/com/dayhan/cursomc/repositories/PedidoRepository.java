package br.com.dayhan.cursomc.repositories;

import br.com.dayhan.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PedidoRepository
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}