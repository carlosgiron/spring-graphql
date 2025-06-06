package com.example.graphql.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario con ID " + id + " no encontrado.");
    }
}