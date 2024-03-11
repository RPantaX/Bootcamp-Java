package com.codigo.practicasonar.service;

import com.codigo.practicasonar.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService {
    ResponseEntity<Usuario>  crearUsuario(Usuario usuario);

    ResponseEntity<Usuario>  obtenerUsuario(Long id);

}
