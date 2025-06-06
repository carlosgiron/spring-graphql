package com.example.graphql.infrastructure.adapter.output.jpa;

import com.example.graphql.domain.model.Usuario;
import com.example.graphql.domain.ports.output.UsuarioRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return  repository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
