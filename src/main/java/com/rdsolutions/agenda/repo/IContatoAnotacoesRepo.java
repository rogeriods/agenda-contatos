package com.rdsolutions.agenda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdsolutions.agenda.model.ContatoAnotacoes;

public interface IContatoAnotacoesRepo extends JpaRepository<ContatoAnotacoes, Long> {
    
}
