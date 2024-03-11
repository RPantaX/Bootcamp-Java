package com.codigo.examen.controller;

import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(authenticationService);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void testSigninSuccess() throws Exception {
        // Arrange
        SignInRequest signInRequest = new SignInRequest("username", "password");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");
        when(authenticationService.signIn(signInRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponse> responseEntity = authController.signin(signInRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
        verify(authenticationService, times(1)).signIn(signInRequest);
    }
}