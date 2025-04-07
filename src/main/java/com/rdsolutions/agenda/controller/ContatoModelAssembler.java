package com.rdsolutions.agenda.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.rdsolutions.agenda.model.Contato;

@Component
public class ContatoModelAssembler implements RepresentationModelAssembler<Contato, EntityModel<Contato>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Contato> toModel(Contato contato) {
        return EntityModel.of(contato,
            linkTo(methodOn(ContatoController.class).getById(contato.getId())).withSelfRel(),
            linkTo(methodOn(ContatoController.class).getAll()).withRel("contatos"));
    }
    
}