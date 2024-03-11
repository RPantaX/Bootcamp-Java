package com.codigo.examen.service;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.request.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService {
    UserDetailsService userDetailsService();
    ResponseEntity<Usuario> createUsuario(SignUpRequest usuario);
    ResponseEntity<Usuario> getUsuarioById(Long id);
    ResponseEntity<Usuario> updateUsuario(Long id, SignUpRequest usuario);
    ResponseEntity<Usuario> deleteUsuario(Long id);
}
