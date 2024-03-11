package com.codigo.examen.controller;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.request.SignUpRequest;
import com.codigo.examen.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;

public class UsuarioControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioController = new UsuarioController(usuarioService);
    }
    @Test
    void testCreateUsuarioSuccess() {
        // Arrange
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("testUser")
                .email("test@example.com")
                .telefono("123456789")
                .password("testPassword")
                .roles(Collections.singleton("USER"))
                .build();

        Usuario usuario = new Usuario();
        when(usuarioService.createUsuario(signUpRequest)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(usuario));

        // Act
        ResponseEntity<Usuario> responseEntity = usuarioController.createUsuario(signUpRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
        verify(usuarioService, times(1)).createUsuario(signUpRequest);
        assertEquals(responseEntity.getBody().getUsername(), usuario.getUsername());
        assertEquals(responseEntity.getBody().getEmail(), usuario.getEmail());
        assertEquals(responseEntity.getBody().getTelefono(), usuario.getTelefono());
    }

    @Test
    void testGetUsuarioByIdSuccess() {
        // Arrange
        Long userId = 1L;
        Usuario usuario = new Usuario();
        when(usuarioService.getUsuarioById(userId)).thenReturn(ResponseEntity.ok(usuario));

        // Act
        ResponseEntity<Usuario> responseEntity = usuarioController.getUsuarioById(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
        verify(usuarioService, times(1)).getUsuarioById(userId);
    }

    @Test
    void testUpdateUsuarioSuccess() {
        // Arrange
        Long userId = 1L;
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("testUser")
                .email("test@example.com")
                .telefono("123456789")
                .password("testPassword")
                .roles(Collections.singleton("USER"))
                .build();

        Usuario usuario = new Usuario();
        when(usuarioService.updateUsuario(userId, signUpRequest)).thenReturn(ResponseEntity.ok(usuario));

        // Act
        ResponseEntity<Usuario> responseEntity = usuarioController.updateUsuario(userId, signUpRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
        verify(usuarioService, times(1)).updateUsuario(userId, signUpRequest);
        assertEquals(responseEntity.getBody().getUsername(), usuario.getUsername());
        assertEquals(responseEntity.getBody().getEmail(), usuario.getEmail());
        assertEquals(responseEntity.getBody().getTelefono(), usuario.getTelefono());
    }

    @Test
    void testDeleteUsuarioSuccess() {
        // Arrange
        Long userId = 1L;
        when(usuarioService.deleteUsuario(userId)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<?> responseEntity = usuarioController.deleteUsuario(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(usuarioService, times(1)).deleteUsuario(userId);
    }
}
