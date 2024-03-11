package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByUsername(username).orElseThrow( ()->
                        new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }
    @Transactional
    @Override
    public ResponseEntity<Usuario> createUsuario(SignUpRequest usuario) {
        Optional<Usuario> existingUser = usuarioRepository.findByUsername(usuario.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        return getUsuarioResponseEntity(usuario);
    }

    @Override
    public ResponseEntity<Usuario> getUsuarioById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Usuario> updateUsuario(Long id, SignUpRequest usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            usuario.setIdUsuario(id);

            if (!usuario.getUsername().equals(existingUsuario.get().getUsername())) {
                Optional<Usuario> userWithNewUsername = usuarioRepository.findByUsername(usuario.getUsername());
                if (userWithNewUsername.isPresent()) {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            return getUsuarioResponseEntity(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Usuario> deleteUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    private ResponseEntity<Usuario> getUsuarioResponseEntity(SignUpRequest usuario) {
        Set<String> assignedRoles = new HashSet<>();
        Set<Rol> roles= new HashSet<>();
        for (String rol : usuario.getRoles()) {
            Optional<Rol> rolEntity = rolRepository.findByNombreRol(rol.toUpperCase());
            if (!rolEntity.isPresent()) {
                roles.add(rolRepository.save(Rol.builder().nombreRol(rol.toUpperCase()).build()));
            }
            else roles.add(rolEntity.get());
            assignedRoles.add(rol.toUpperCase());
        }
        return insertarDatos(usuario,roles);
    }
    private ResponseEntity<Usuario> insertarDatos(SignUpRequest usuario, Set<Rol> roles){
        return new ResponseEntity<>(usuarioRepository.save(
                Usuario.builder()
                        .username(usuario.getUsername())
                        .email(usuario.getEmail())
                        .telefono(usuario.getTelefono())
                        .roles(roles)
                        .password(new BCryptPasswordEncoder().encode(usuario.getPassword()))
                        .accountnonexpire(true)
                        .credentialsnonexpired(true)
                        .accountnonlocked(true)
                        .enabled(true)
                        .build()
        ), HttpStatus.CREATED);
    }



}
