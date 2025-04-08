package com.rdsolutions.agenda.controller;

import com.rdsolutions.agenda.exceptions.ContatoNotFoundException;
import com.rdsolutions.agenda.model.Contato;
import com.rdsolutions.agenda.repo.IContatoRepo;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/contatos")
public class ContatoController {

    private final IContatoRepo repo;
    private final ContatoModelAssembler assembler;

    public ContatoController(IContatoRepo repo, ContatoModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Contato>> getAll() {
        List<EntityModel<Contato>> contatos = repo.findAll().stream()
            .map(assembler::toModel) 
            .collect(Collectors.toList());

        return CollectionModel.of(contatos, linkTo(methodOn(ContatoController.class).getAll()).withSelfRel());
    }

    // The return type of the method has changed from Contato to EntityModel<Contato>.
    // EntityModel<T> generic container from Spring HATEOAS that includes not only the data but a collection of links.
    @GetMapping("/{id}")
    public EntityModel<Contato> getById(@PathVariable Long id) {
        Contato contato = repo.findById(id).orElseThrow(() -> new ContatoNotFoundException(id));
        return assembler.toModel(contato);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Contato contato) {
        contato.setDataCriacao(new Date());
        EntityModel<Contato> entityModel = assembler.toModel(repo.save(contato));
        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replace(@RequestBody Contato newContato, @PathVariable Long id) {
        Contato updatedContato = repo.findById(id)
            .map(contato -> {
                contato.setNome(newContato.getNome());
                contato.setEmail(newContato.getEmail());
                contato.setTelefone(newContato.getTelefone());
                contato.setTipo(newContato.getTipo());
                contato.setDataCriacao(new Date());
                return repo.save(contato);
            })
            .orElseGet(() -> repo.save(newContato));
        
        EntityModel<Contato> entityModel = assembler.toModel(updatedContato);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
