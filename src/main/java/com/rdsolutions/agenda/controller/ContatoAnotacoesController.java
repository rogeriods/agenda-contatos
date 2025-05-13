package com.rdsolutions.agenda.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdsolutions.agenda.exceptions.ContatoAnotacoesNotFoundException;
import com.rdsolutions.agenda.model.ContatoAnotacoes;
import com.rdsolutions.agenda.model.Status;
import com.rdsolutions.agenda.repo.IContatoAnotacoesRepo;

@RestController
@RequestMapping("/v1/contatos/anotacoes")
class ContatoAnotacoesController {

    private final IContatoAnotacoesRepo repo;
    private final ContatoAnotacoesModelAssembler assembler;

    public ContatoAnotacoesController(IContatoAnotacoesRepo repo, ContatoAnotacoesModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<ContatoAnotacoes>> getAll() {
        List<EntityModel<ContatoAnotacoes>> cAnotacoes = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cAnotacoes, linkTo(methodOn(ContatoAnotacoesController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<ContatoAnotacoes> getById(@PathVariable Long id) {
        ContatoAnotacoes cAnotacoes = repo.findById(id).orElseThrow(() -> new ContatoAnotacoesNotFoundException(id));
        return assembler.toModel(cAnotacoes);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ContatoAnotacoes>> newContatoAnotacoes(@RequestBody ContatoAnotacoes cAnotacoes) {
        cAnotacoes.setStatus(Status.ATIVO);
        ContatoAnotacoes newCAnotacoes = repo.save(cAnotacoes);

        return ResponseEntity
                .created(linkTo(methodOn(ContatoAnotacoesController.class)
                .getById(newCAnotacoes.getId())).toUri())
                .body(assembler.toModel(newCAnotacoes));
    }
}