package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Pedido;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;

	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public Pedido find(Integer id) {
		return this.pedidoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(
						"Objeto não encontrado: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
	}
}
