package br.com.dayhan.cursomc.repositories;

import br.com.dayhan.cursomc.domain.ItemPedido;
import br.com.dayhan.cursomc.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ItemPedidoRepository
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}