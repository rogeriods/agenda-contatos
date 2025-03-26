package com.rdsolutions.agenda.repo;

import com.rdsolutions.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContatoRepo extends JpaRepository<Contato, Long> {
}
