package com.rdsolutions.agenda.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.rdsolutions.agenda.model.ContatoAnotacoes;

@Component
class ContatoAnotacoesModelAssembler
        implements RepresentationModelAssembler<ContatoAnotacoes, EntityModel<ContatoAnotacoes>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<ContatoAnotacoes> toModel(ContatoAnotacoes cAnotacoes) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<ContatoAnotacoes> cAModel = EntityModel.of(cAnotacoes,
                linkTo(methodOn(ContatoAnotacoesController.class).getById(cAnotacoes.getId())).withSelfRel(),
                linkTo(methodOn(ContatoAnotacoesController.class).getAll()).withRel("anotacoes"));

        // Conditional links based on state of the order
        /*if (cAnotacoes.getStatus() == Status.INATIVO) {
            // TODO: CREATE SOMETHING FOR THIS STATE
            cAModel.add(
                    linkTo(methodOn(ContatoAnotacoesController.class).cancel(cAnotacoes.getId())).withRel("cancel"));
            cAModel.add(linkTo(methodOn(ContatoAnotacoesController.class).complete(cAnotacoes.getId()))
                    .withRel("complete"));
        }*/

        return cAModel;
    }
}