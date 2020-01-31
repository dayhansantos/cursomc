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
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.repositories.EstadoRepository;
import br.com.dayhan.cursomc.repositories.PagamentoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

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

        final Produto p1 	= new Produto("Computador", 2000.0);
        final Produto p2 	= new Produto("Impressora", 800.0);
        final Produto p3 	= new Produto("Mouse", 80.0);
        final Produto p4 	= new Produto("Mesa de escritório", 300.0);
        final Produto p5 	= new Produto("Toalha", 50.0);
        final Produto p6 	= new Produto("Colcha", 200.0);
        final Produto p7 	= new Produto("TV true color", 1200.0);
        final Produto p8 	= new Produto("Roçadeira", 800.0);
        final Produto p9 	= new Produto("Abajour", 100.0);
        final Produto p10 	= new Produto("Pendente", 180.0);
        final Produto p11 	= new Produto("Shampoo", 90.0);

        cat1.addProduto(p1, p2, p3);
        cat2.addProduto(p2, p4);
        cat3.addProduto(p5, p6);
        cat4.addProduto(p1, p2, p3, p7);
        cat5.addProduto(p8);
        cat6.addProduto(p9, p10);
        cat7.addProduto(p11);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

        final Estado est1 = new Estado("Minas Gerais");
        final Estado est2 = new Estado("São Paulo");

        final Cidade c1 = new Cidade(null, "Uberlândia", est1);
        final Cidade c2 = new Cidade(null, "São Paulo", est2);
        final Cidade c3 = new Cidade(null, "Campinas", est2);
        
        est1.addCidade(c1);
        est2.addCidade(c2, c3);

        estadoRepository.saveAll(Arrays.asList(est1, est2));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PF);
        cli1.addTelefone("123456789", "1122334455");

        Endereco e1 = new Endereco("Rua flores", "300", "Apt 303", "Jardim", "234234234", c1);
        Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "213123123", c2);
        cli1.addEndereco(e1, e2);

        clienteRepository.save(cli1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("10/09/2017 10:32"), e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);
        
        cli1.addPedido(ped1, ped2);

        ItemPedido ip1 = new ItemPedido(0.0, 1, 2000.0);
        ItemPedido ip2 = new ItemPedido(0.0, 2, 80.0);
        ItemPedido ip3 = new ItemPedido(100.0, 1, 800.0);
        
        ped1.addItemPedido(ip1, ip2);
        ped2.addItemPedido(ip3);

        p1.addItemPedido(ip1);
        p2.addItemPedido(ip3);
        p3.addItemPedido(ip2);

        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
    }
}
