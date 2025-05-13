package com.rdsolutions.agenda.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTATOS_ANOTACOES")
public class ContatoAnotacoes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String anotacao;
    public Status status;

    // constructor
    public ContatoAnotacoes() {}
    public ContatoAnotacoes(String anotacao, Status status) {
        this.anotacao = anotacao;
        this.status = status;
    }

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAnotacao() {
        return anotacao;
    }
    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    // toString, hash, and equals
    @Override
    public String toString() {
        return "ContatoAnotacoes [id=" + id + ", anotacao=" + anotacao + ", status=" + status + "]";
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.anotacao, this.status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContatoAnotacoes other = (ContatoAnotacoes) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }      
}
