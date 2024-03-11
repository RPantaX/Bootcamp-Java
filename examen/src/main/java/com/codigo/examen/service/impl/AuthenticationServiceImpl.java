package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AuthenticationResponse;
import com.codigo.examen.service.AuthenticationService;
import com.codigo.examen.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

   private final UsuarioRepository usuarioRepository;
   private final AuthenticationManager authenticationManager;
   private final JWTService jwtService;
    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword()));
        try {
            AuthenticationResponse jwt = new AuthenticationResponse();
            Optional<Usuario> user = usuarioRepository.findByUsername(signInRequest.getUsername());
            if (user.isEmpty()) {
                jwt.setToken("User not registered");
                return jwt;
            }
            // verificar la contraseña
            if (verifyPassword(signInRequest.getPassword(), user.get().getPassword())) {
                jwt.setToken(jwtService.generateToken(user.get()));
            } else{
                jwt.setToken("Authentication failed");
            }
            return jwt;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword); // si hace match true, si no false
    }
}
