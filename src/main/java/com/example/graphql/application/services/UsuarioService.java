package com.example.graphql.application.services;

import com.example.graphql.domain.model.Usuario;
import com.example.graphql.domain.ports.input.UsuarioUseCase;
import com.example.graphql.domain.ports.output.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioUseCase {


    private final UsuarioRepositoryPort repository;
    private final Sinks.Many<Usuario> sink = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public Usuario crear(String nombre, String correo) {
        var nuevo = repository.save(Usuario.builder().nombre(nombre).correo(correo).build());
        sink.tryEmitNext(nuevo);
        return nuevo;
    }

    @Override
    public Usuario actualizar(Long id, String nombre, String correo) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        if (nombre != null) usuario.setNombre(nombre);
        if (correo != null) usuario.setCorreo(correo);
        return repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Sinks.Many<Usuario> getSink() {
        return sink;
    }
}