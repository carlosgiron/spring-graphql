package com.example.graphql.domain.ports.input;


import com.example.graphql.domain.model.Usuario;
import reactor.core.publisher.Sinks;

import java.util.List;

public interface UsuarioUseCase {
    Usuario crear(String nombre, String correo);
    Usuario actualizar(Long id, String nombre, String correo);
    void eliminar(Long id);
    Usuario obtenerPorId(Long id);
    List<Usuario> listar();
    Sinks.Many<Usuario> getSink();
}