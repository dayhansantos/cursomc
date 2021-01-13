package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.*;
import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.repositories.EstadoRepository;
import br.com.dayhan.cursomc.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {
    private CategoriaRepository categoriaRepository;
    private EstadoRepository estadoRepository;
    private ClienteRepository clienteRepository;
    private PagamentoRepository pagamentoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DBService(
            CategoriaRepository categoriaRepository,
            EstadoRepository estadoRepository,
            ClienteRepository clienteRepository,
            PagamentoRepository pagamentoRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.categoriaRepository = categoriaRepository;
        this.estadoRepository = estadoRepository;
        this.clienteRepository = clienteRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<Pagamento> instantiateDatabase() throws Exception {
        final var cat1 = new Categoria("Informática");
        final var cat2 = new Categoria("Escritório");
        final var cat3 = new Categoria("Cama mesa e banho");
        final var cat4 = new Categoria("Eletrônicos");
        final var cat5 = new Categoria("Jardinagem");
        final var cat6 = new Categoria("Decoração");
        final var cat7 = new Categoria("Perfumaria");

        final var p1 	= new Produto("Computador", 2000.0);
        final var p2 	= new Produto("Impressora", 800.0);
        final var p3 	= new Produto("Mouse", 80.0);
        final var p4 	= new Produto("Mesa de escritório", 300.0);
        final var p5 	= new Produto("Toalha", 50.0);
        final var p6 	= new Produto("Colcha", 200.0);
        final var p7 	= new Produto("TV true color", 1200.0);
        final var p8 	= new Produto("Roçadeira", 800.0);
        final var p9 	= new Produto("Abajour", 100.0);
        final var p10 	= new Produto("Pendente", 180.0);
        final var p11 	= new Produto("Shampoo", 90.0);

        cat1.addProduto(p1, p2, p3);
        cat2.addProduto(p2, p4);
        cat3.addProduto(p5, p6);
        cat4.addProduto(p1, p2, p3, p7);
        cat5.addProduto(p8);
        cat6.addProduto(p9, p10);
        cat7.addProduto(p11);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

        final var est1 = new Estado("Minas Gerais");
        final var est2 = new Estado("São Paulo");

        final var c1 = new Cidade(null, "Uberlândia", est1);
        final var c2 = new Cidade(null, "São Paulo", est2);
        final var c3 = new Cidade(null, "Campinas", est2);

        est1.addCidade(c1);
        est2.addCidade(c2, c3);

        estadoRepository.saveAll(Arrays.asList(est1, est2));

        var cli1 = new Cliente(null, "Dayhan Henrique", "dayhantest@gmail.com", "36378912377", TipoCliente.PF, bCryptPasswordEncoder.encode("1234"));
        cli1.addTelefone("123456789", "1122334455");

        var e1 = new Endereco("Rua flores", "300", "Apt 303", "Jardim", "234234234", c1);
        var e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "213123123", c2);
        cli1.addEndereco(e1, e2);

        clienteRepository.save(cli1);

        var sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        var ped1 = new Pedido(null, sdf.parse("10/09/2017 10:32"), e1);
        var ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2);

        var pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        var pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.addPedido(ped1, ped2);

        var ip1 = new ItemPedido(0.0, 1, 2000.0, p1);
        var ip2 = new ItemPedido(0.0, 2, 80.0, p3);
        var ip3 = new ItemPedido(100.0, 1, 800.0, p2);

        ped1.addItemPedido(ip1, ip2);
        ped2.addItemPedido(ip3);

        return pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
    }
}
