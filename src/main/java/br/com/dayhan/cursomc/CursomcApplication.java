package br.com.dayhan.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dayhan.cursomc.domain.Categoria;
import br.com.dayhan.cursomc.domain.Cidade;
import br.com.dayhan.cursomc.domain.Cliente;
import br.com.dayhan.cursomc.domain.Endereco;
import br.com.dayhan.cursomc.domain.Estado;
import br.com.dayhan.cursomc.domain.ItemPedido;
import br.com.dayhan.cursomc.domain.Pagamento;
import br.com.dayhan.cursomc.domain.PagamentoComBoleto;
import br.com.dayhan.cursomc.domain.PagamentoComCartao;
import br.com.dayhan.cursomc.domain.Pedido;
import br.com.dayhan.cursomc.domain.Produto;
import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import br.com.dayhan.cursomc.repositories.CidadeRepository;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.repositories.EnderecoRepository;
import br.com.dayhan.cursomc.repositories.EstadoRepository;
import br.com.dayhan.cursomc.repositories.ItemPedidoRepository;
import br.com.dayhan.cursomc.repositories.PagamentoRepository;
import br.com.dayhan.cursomc.repositories.PedidoRepository;
import br.com.dayhan.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(final String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final Categoria cat1 = new Categoria("Informática");
        final Categoria cat2 = new Categoria("Escritório");
        final Categoria cat3 = new Categoria("Cama mesa e banho");
        final Categoria cat4 = new Categoria("Eletrônicos");
        final Categoria cat5 = new Categoria("Jardinagem");
        final Categoria cat6 = new Categoria("Decoração");
        final Categoria cat7 = new Categoria("Perfumaria");

        final Produto p1 = new Produto("Computador", 2000.0);
        final Produto p2 = new Produto("Impressora", 800.0);
        final Produto p3 = new Produto("Mouse", 80.0);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        final Estado est1 = new Estado("Minas Gerais");
        final Estado est2 = new Estado("São Paulo");

        final Cidade c1 = new Cidade("Uberlândia", est1);
        final Cidade c2 = new Cidade("São Paulo", est2);
        final Cidade c3 = new Cidade("Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PF);
        cli1.getTelefones().addAll(Arrays.asList("123456789", "1122334455"));

        Endereco e1 = new Endereco("Rua flores", "300", "Apt 303", "Jardim", "234234234", cli1, c1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "213123123", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("10/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
                null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
