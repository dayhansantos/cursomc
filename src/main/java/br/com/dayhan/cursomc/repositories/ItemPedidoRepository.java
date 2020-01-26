package br.com.dayhan.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dayhan.cursomc.domain.ItemPedido;
import br.com.dayhan.cursomc.domain.ItemPedidoPK;

/**
 * ItemPedidoRepository
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}