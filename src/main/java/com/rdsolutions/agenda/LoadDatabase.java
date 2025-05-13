package com.rdsolutions.agenda;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rdsolutions.agenda.model.Contato;
import com.rdsolutions.agenda.model.ContatoAnotacoes;
import com.rdsolutions.agenda.model.Status;
import com.rdsolutions.agenda.repo.IContatoAnotacoesRepo;
import com.rdsolutions.agenda.repo.IContatoRepo;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IContatoRepo repoC, IContatoAnotacoesRepo repoCA) {
        return args -> {
            log.info("Preloading {}", repoC.save(new Contato("Bilbo Baggins", "bilbo@outlook.com", "+55 11 98888.0000", "Pessoal", new Date())));
            log.info("Preloading {}", repoC.save(new Contato("Frodo Baggins", "frodo@outlook.com", "+55 11 98888.0001", "Pessoal", new Date())));
        
            log.info("Preloading {}", repoCA.save(new ContatoAnotacoes("Bilbo's notations", Status.ATIVO)));   
            log.info("Preloading {}", repoCA.save(new ContatoAnotacoes("Frodo's notations", Status.INATIVO)));   
        };
    }
}
