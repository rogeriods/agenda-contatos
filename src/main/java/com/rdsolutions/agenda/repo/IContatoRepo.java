package com.rdsolutions.agenda.repo;

import com.rdsolutions.agenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

// spring data's repository makes it possible to sidestpe data store
public interface IContatoRepo extends JpaRepository<Contato, Long> {
}
