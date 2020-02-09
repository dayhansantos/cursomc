package br.com.dayhan.cursomc;

import br.com.dayhan.cursomc.domain.*;
import br.com.dayhan.cursomc.domain.enums.EstadoPagamento;
import br.com.dayhan.cursomc.domain.enums.TipoCliente;
import br.com.dayhan.cursomc.repositories.CategoriaRepository;
import br.com.dayhan.cursomc.repositories.ClienteRepository;
import br.com.dayhan.cursomc.repositories.EstadoRepository;
import br.com.dayhan.cursomc.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Override
    public void run(final String... args) {

    }

    public static void main(final String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }
}
