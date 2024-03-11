package com.codigo.examen.controller;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ms-examen/v1")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping("/crearusuarios")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody SignUpRequest signUpRequest) {
        return usuarioService.createUsuario(signUpRequest);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody SignUpRequest usuario) {
        return usuarioService.updateUsuario(id,usuario);
    }
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        return usuarioService.deleteUsuario(id);
    }
}
