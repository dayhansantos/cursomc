package br.com.dayhan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dayhan.cursomc.domain.Pedido;

/**
 * PedidoRepository
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}