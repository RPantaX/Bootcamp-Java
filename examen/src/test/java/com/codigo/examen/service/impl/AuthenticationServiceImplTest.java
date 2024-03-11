package com.codigo.examen.service.impl;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.JWTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AuthenticationServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    Usuario usuarioUser;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationServiceImpl(usuarioRepository,authenticationManager, jwtService);
    }
    @BeforeEach
    void guardarUsuario(){
        usuarioUser = Usuario.builder()
                .username("username")
                .email("jiji@gmail.com")
                .telefono("987654321")
                .password(new BCryptPasswordEncoder().encode("rarito29#.sl"))
                .accountnonexpire(true)
                .credentialsnonexpired(true)
                .accountnonlocked(true)
                .enabled(true)
                .build();

    }
    @DisplayName("Test para iniciar sesión correctamente")
    @Test
    void signIn_WithValidCredentials_ReturnsToken() throws Exception {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("username", "rarito29#.sl");
        Usuario usuario = usuarioUser;
        when(usuarioRepository.findByUsername(signInRequest.getUsername())).thenReturn(Optional.of(usuarioUser));
        when(jwtService.generateToken(usuario)).thenReturn("Tokeeeeeeeeeeeen");

        // Act
        String token = authenticationService.signIn(signInRequest).getToken();

        // Assert
        assertEquals("Tokeeeeeeeeeeeen", token);
    }

    @DisplayName("Test para iniciar sesión con usuario no registrado")
    @Test
    void signInUserNotRegistered() throws Exception {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("username", "password");
        when(usuarioRepository.findByUsername(signInRequest.getUsername())).thenReturn(Optional.empty());

        // Act
        String token = authenticationService.signIn(signInRequest).getToken();

        // Assert
        assertEquals("User not registered", token);
    }

    @DisplayName("Test para iniciar sesión con contraseña incorrecta")
    @Test
    void signInAuthenticationFailed() throws Exception {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("username", "wrongPassword");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setUsername(signInRequest.getUsername());
        usuario.setPassword("$2a$10$4Gfyj7TCXT5zBiYJ9LXcmeAEC3FwRnXb1CRJPBt2dmZZCIKs1mQ7y"); // Contraseña encriptada

        when(usuarioRepository.findByUsername(signInRequest.getUsername())).thenReturn(Optional.of(usuario));

        // Act
        AuthenticationResponse response = authenticationService.signIn(signInRequest);

        // Assert
        assertEquals("Authentication failed", response.getToken());
        verify(authenticationManager).authenticate(any()); // Verifica que se haya llamado al authenticationManager.authenticate
    }
}