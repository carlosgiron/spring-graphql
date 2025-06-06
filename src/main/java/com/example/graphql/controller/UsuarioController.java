package com.example.graphql.controller;

import com.example.graphql.domain.model.Usuario;
import com.example.graphql.domain.ports.input.UsuarioUseCase;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UsuarioController {

    private final UsuarioUseCase useCase;


    public UsuarioController(UsuarioUseCase useCase) {
        this.useCase = useCase;
    }


    @QueryMapping
    public Usuario usuario(@Argument Long id) {
        return useCase.obtenerPorId(id);
    }

    @QueryMapping
    public List<Usuario> usuarios() {
        return useCase.listar();
    }

    @MutationMapping
    public Usuario crearUsuario(@Argument String nombre, @Argument String correo) {
        return useCase.crear(nombre, correo);
    }

    @MutationMapping
    public Usuario actualizarUsuario(@Argument Long id, @Argument String nombre, @Argument String correo) {
        return useCase.actualizar(id, nombre, correo);
    }

    @MutationMapping
    public boolean eliminarUsuario(@Argument Long id) {
        useCase.eliminar(id);
        return true;
    }
    @SubscriptionMapping
    public Publisher<Usuario> usuarioCreado() {
        return useCase.getSink().asFlux();
    }
}
