package br.com.dayhan.cursomc.config;

import br.com.dayhan.cursomc.services.DBService;
import br.com.dayhan.cursomc.services.EmailService;
import br.com.dayhan.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDatabase() throws Exception {
        return this.dbService.instantiateDatabase() != null;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
