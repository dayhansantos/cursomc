package br.com.dayhan.cursomc.config;

import br.com.dayhan.cursomc.services.DBService;
import br.com.dayhan.cursomc.services.EmailService;
import br.com.dayhan.cursomc.services.SMTPEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instanciateDatabase() throws Exception {
        if(!strategy.equals("create")) {
            return false;
        }
        return this.dbService.instantiateDatabase() != null;
    }

    @Bean
    public EmailService emailService() {
        return new SMTPEmailService();
    }
}
