package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JWTServiceImplTest {
    @InjectMocks
    private JWTServiceImpl jwtService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        jwtService = new JWTServiceImpl();
    }
    Usuario usuarioUser;
    Resource resource;
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
        resource = new FileSystemResource("src/main/resources/jwtKeys/private_key.pem");
        ReflectionTestUtils.setField(jwtService, "privateKeyResource", resource);
    }
    @Test
    @DisplayName("Test generar token")
    void generateTokenTest() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Arrange
        UserDetails userDetails = usuarioUser;
        // Act
        String token = jwtService.generateToken(userDetails);

        // Assert
        assertNotNull(token);
    }

    @Test
    @DisplayName("Test validacion del token")
    void validarToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Arrange
        UserDetails userDetails = usuarioUser;
        String token = jwtService.generateToken(userDetails);

        // Act y Assert
        assertTrue(jwtService.validateToken(token, userDetails));
    }

    @Test
    @DisplayName("Test validar token expirado")
    void validarTokenExpirado() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Resource resource1 = resource;
        // Arrange
        UserDetails userDetails = usuarioUser;
        String token = generarTokenExpirado(userDetails, resource1);

        // Act y Assert
        assertThrows(Exception.class, ()-> jwtService.validateToken(token, userDetails));

    }

    @Test
    @DisplayName("Test extraer username desde el token")
    void extraerUsername() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Arrange
        UserDetails userDetails = usuarioUser;
        String token = jwtService.generateToken(userDetails);
        // Act
        String extractedUsername = jwtService.extractUserName(token);

        // Assert
        assertEquals("username", extractedUsername);
    }

    private String generarTokenExpirado(UserDetails userDetails, Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() - 120000))
                .signWith(jwtService.getSignKey(resource), SignatureAlgorithm.RS256)
                .compact();
    }
}