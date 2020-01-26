package br.com.dayhan.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dayhan.cursomc.domain.Pedido;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		return this.pedidoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(
						"Objeto não encontrado: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
	}
}
