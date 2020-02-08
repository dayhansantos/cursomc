package br.com.dayhan.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dayhan.cursomc.domain.ItemPedido;
import br.com.dayhan.cursomc.domain.PagamentoComBoleto;
import br.com.dayhan.cursomc.domain.Pedido;
import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import br.com.dayhan.cursomc.exception.NotFoundException;
import br.com.dayhan.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private BoletoService boletoService;

    public Pedido find(Integer id) {
        return this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Objeto não encontrado: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }

        obj.getItens().forEach(i -> getItemPedido(i, obj));

        return pedidoRepository.save(obj);
    }

    private void getItemPedido(ItemPedido itemPedido, Pedido pedido) {
        itemPedido.setPedido(pedido);
        var produto = produtoService.find(itemPedido.getProduto().getId());
        itemPedido.setDesconto(0.0);
        itemPedido.setQuantidade(itemPedido.getQuantidade());
        itemPedido.setProduto(produto);
        itemPedido.setPreco(produto.getPreco());
    }
}
