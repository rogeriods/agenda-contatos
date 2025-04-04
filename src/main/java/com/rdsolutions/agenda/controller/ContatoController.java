package com.rdsolutions.agenda.controller;

import com.rdsolutions.agenda.exceptions.ContatoNotFoundException;
import com.rdsolutions.agenda.model.Contato;
import com.rdsolutions.agenda.repo.IContatoRepo;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/contatos")
public class ContatoController {

    private final IContatoRepo repo;

    public ContatoController(IContatoRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Contato> getAll() {
        return repo.findAll();
    }

    // The return type of the method has changed from Contato to EntityModel<Contato>.
    // EntityModel<T> generic container from Spring HATEOAS that includes not only the data but a collection of links.
    @GetMapping("/{id}")
    public EntityModel<Contato> getById(@PathVariable Long id) {
        Contato contato = repo.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));

        return EntityModel.of(contato,
                linkTo(methodOn(ContatoController.class).getById(id)).withSelfRel(), // Selected reference
                linkTo(methodOn(ContatoController.class).getAll()).withRel("contatos")); // Get all contacts
    }

    @PostMapping
    public Contato create(@RequestBody Contato contato) {
        contato.setDataCriacao(new Date());
        return repo.save(contato);
    }

    @PutMapping("/{id}")
    public Contato replace(@RequestBody Contato newContato, @PathVariable Long id) {
        return repo.findById(id)
                .map(contato -> {
                    contato.setNome(newContato.getNome());
                    contato.setEmail(newContato.getEmail());
                    contato.setTelefone(newContato.getTelefone());
                    contato.setTipo(newContato.getTipo());
                    contato.setDataCriacao(new Date());
                    return repo.save(contato);
                })
                .orElseGet(() -> repo.save(newContato));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
