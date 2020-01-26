package br.com.dayhan.cursomc;

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
import br.com.dayhan.cursomc.domain.Produto;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import br.com.dayhan.cursomc.repositories.CidadeRepository;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.repositories.EnderecoRepository;
import br.com.dayhan.cursomc.repositories.EstadoRepository;
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

    public static void main(final String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final Categoria cat1 = new Categoria("Informática");
        final Categoria cat2 = new Categoria("Escritório");

        final Produto p1 = new Produto("Computador", 2000.0);
        final Produto p2 = new Produto("Impressora", 800.0);
        final Produto p3 = new Produto("Mouse", 80.0);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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

    }
}
