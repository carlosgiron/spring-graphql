package com.example.graphql.infrastructure.adapter.output.jpa;


import com.example.graphql.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {}