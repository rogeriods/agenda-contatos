package com.rdsolutions.agenda;

import com.rdsolutions.agenda.model.Contato;
import com.rdsolutions.agenda.repo.IContatoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IContatoRepo repo) {
        return args -> {
            log.info("Preloading {}", repo.save(new Contato("Bilbo Baggins", "bilbo@outlook.com", "+55 11 98888.0000", "Pessoal", new Date())));
            log.info("Preloading {}", repo.save(new Contato("Frodo Baggins", "frodo@outlook.com", "+55 11 98888.0001", "Pessoal", new Date())));
        };
    }
}
