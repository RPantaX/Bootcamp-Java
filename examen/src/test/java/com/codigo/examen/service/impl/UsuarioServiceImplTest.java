package com.codigo.examen.service.impl;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RolRepository rolRepository;
    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    Usuario usuarioUser;
    Usuario usuarioAdmin;
    SignUpRequest signUpRequestUser;
    SignUpRequest signUpRequestAdmin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository,rolRepository);
    }
    @BeforeEach
    void guardarUsuarios(){
        String user = "USER";
        String admin = "ADMIN";
        Set<String> rolUser= new HashSet<>();
        Set<String> roleAdmin= new HashSet<>();
        Set< Rol> rolAdminEntity = new HashSet<>();
        Set< Rol> rolUserEntity = new HashSet<>();
        rolUser.add(user);
        roleAdmin.add(admin);
        rolAdminEntity.add(Rol.builder().nombreRol(admin).build());
        rolUserEntity.add(Rol.builder().nombreRol(user).build());

        signUpRequestUser=SignUpRequest.builder()
                .username("Jefferson")
                .email("jeff@gmail.com")
                .telefono("987654321")
                .password("RPantaX123#")
                .roles(rolUser)
                .build();

        signUpRequestAdmin = SignUpRequest.builder()
                .username("Carlos")
                .email("Carlos@gmail.com")
                .telefono("930889076")
                .password("RCarlosX123#")
                .roles(roleAdmin)
                .build();
        usuarioUser = Usuario.builder()
                .username(signUpRequestUser.getUsername())
                .email(signUpRequestUser.getEmail())
                .telefono(signUpRequestUser.getTelefono())
                .roles(rolUserEntity)
                .password(new BCryptPasswordEncoder().encode(signUpRequestUser.getPassword()))
                .accountnonexpire(true)
                .credentialsnonexpired(true)
                .accountnonlocked(true)
                .enabled(true)
                .build();
        usuarioAdmin = Usuario.builder()
                .username("Carlos")
                .email("Carlos@gmail.com")
                .telefono("930889076")
                .password("RCarlosX123#")
                .roles(rolAdminEntity)
                .build();
    }
    @Test
    @DisplayName("Test para cargar un usuario existente por nombre de usuario")
    void loadUserByUsernameExistingUser() {
        // Arrange
        String username = "testuser";
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));

        // Act
        UserDetails userDetails = usuarioService.userDetailsService().loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    @DisplayName("Test para cargar un usuario que no existe por nombre de usuario")
    void loadUserByUsernameNonExistingUser() {
        // Arrange
        String username = "nonexistinguser";
        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act y Assert
        assertThrows(UsernameNotFoundException.class, () -> usuarioService.userDetailsService().loadUserByUsername(username));
    }
    @DisplayName("Test para guardar un usuario satisfactoriamente")
    @Test
    void createUsuarioSuccess() {
        SignUpRequest signUpRequest = signUpRequestAdmin;
        ResponseEntity<Usuario> usuarioEsperado = getUsuarioResponseEntity(signUpRequest);

        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> usuarioRecibido = usuarioService.createUsuario(signUpRequest);
        //RESULTADOS
        assertEquals(HttpStatus.CREATED, usuarioRecibido.getStatusCode());
        assertEquals(usuarioEsperado.getBody().getIdUsuario(), usuarioRecibido.getBody().getIdUsuario());
        assertEquals(usuarioEsperado.getBody().getUsername(), usuarioRecibido.getBody().getUsername());
        assertEquals(usuarioEsperado.getBody().getEmail(), usuarioRecibido.getBody().getEmail());
        assertEquals(usuarioEsperado.getBody().getTelefono(), usuarioRecibido.getBody().getTelefono());
        assertEquals(usuarioEsperado.getBody().getPassword(), usuarioRecibido.getBody().getPassword());
        assertEquals(usuarioEsperado.getBody().getRoles(), usuarioRecibido.getBody().getRoles());
        assertEquals(usuarioEsperado.getBody().getAuthorities(), usuarioRecibido.getBody().getAuthorities());

    }
    @DisplayName("Test para crear un usuario que ya existe")
    @Test
    void createUsuarioError() {
        SignUpRequest signUpRequest = signUpRequestUser;
        when(usuarioRepository.findByUsername(anyString())).thenThrow(RuntimeException.class);
        //RESULTADOS
        assertThrows(Exception.class, ()-> usuarioService.createUsuario(signUpRequest));
    }

    @DisplayName("Test para listar un usuario satisfactoriamente")
    @Test
    void getUsuarioByIdSuccess() {
        Long id=1L;
        Usuario entity= new Usuario();
        Optional<Usuario> optionalEntity = Optional.of(entity);
        ResponseEntity<Usuario> expected = new ResponseEntity<>(new Usuario(),HttpStatus.OK);
        when(usuarioRepository.findById(id)).thenReturn(optionalEntity);

        ResponseEntity<Usuario> result = usuarioService.getUsuarioById(id);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(expected.getBody().getIdUsuario(), result.getBody().getIdUsuario());
        assertEquals(expected.getBody().getTelefono(), result.getBody().getTelefono());
        assertEquals(expected.getBody().getEmail(), result.getBody().getEmail());
        assertEquals(expected.getBody().getUsername(), result.getBody().getUsername());
        assertEquals(expected.getBody().getRoles(), result.getBody().getRoles());
        assertEquals(expected.getBody().getAuthorities(), result.getBody().getAuthorities());
    }
    @DisplayName("Test para listar un usuario que no existe")
    @Test
    void getUsuarioByIdError() {
        Long id=5L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> result = usuarioService.getUsuarioById(id);

        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }
    @DisplayName("Test para actualizar un usuario satisfactoriamente")
    @Test
    void updateUsuarioSuccess() {
        SignUpRequest signUpRequest = signUpRequestAdmin;
        ResponseEntity<Usuario> usuarioEsperado = getUsuarioResponseEntity(signUpRequestAdmin);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioUser));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        //when(usuarioRepository.save(usuarioUser)).thenReturn(usuarioUser);
        ResponseEntity<Usuario> usuarioRecibido = usuarioService.updateUsuario(usuarioEsperado.getBody().getIdUsuario(), signUpRequest);
        //RESULTADOS
        assertEquals(HttpStatus.CREATED, usuarioRecibido.getStatusCode());
        assertEquals(usuarioEsperado.getBody().getIdUsuario(), usuarioRecibido.getBody().getIdUsuario());
        assertEquals(usuarioEsperado.getBody().getUsername(), usuarioRecibido.getBody().getUsername());
        assertEquals(usuarioEsperado.getBody().getEmail(), usuarioRecibido.getBody().getEmail());
        assertEquals(usuarioEsperado.getBody().getTelefono(), usuarioRecibido.getBody().getTelefono());
        assertEquals(usuarioEsperado.getBody().getPassword(), usuarioRecibido.getBody().getPassword());
        assertEquals(usuarioEsperado.getBody().getRoles(), usuarioRecibido.getBody().getRoles());
        assertEquals(usuarioEsperado.getBody().getAuthorities(), usuarioRecibido.getBody().getAuthorities());
    }
    @DisplayName("Test para actualizar un usuario con id incorrecto")
    @Test
    void updateUsuarioErrorId() {
        SignUpRequest signUpRequest = signUpRequestUser;
        when(usuarioRepository.findById(anyLong())).thenThrow(RuntimeException.class);
        //RESULTADOS
        assertThrows(Exception.class, ()-> usuarioService.updateUsuario(5L,signUpRequest));
    }
    @DisplayName("Test para actualizar un usuario con usuario repetido")
    @Test
    void updateUsuarioErrorUserPresent() {
        // Arrange
        SignUpRequest signUpRequest = signUpRequestAdmin;
        ResponseEntity<Usuario> usuarioEsperado = getUsuarioResponseEntity(signUpRequestAdmin);
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioUser));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuarioAdmin)); // Simulamos que el nuevo nombre de usuario ya existe

        // Act
        ResponseEntity<Usuario> usuarioRecibido = usuarioService.updateUsuario(usuarioEsperado.getBody().getIdUsuario(), signUpRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, usuarioRecibido.getStatusCode()); // Esperamos un estado de error
        assertNull(usuarioRecibido.getBody());
    }
    @DisplayName("Test para eliminar usuario")
    @Test
    void deleteUsuario() {
        long id = 1L;

        Usuario usuario = Usuario.builder()
                .idUsuario(id)
                .build();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Llamar al método de servicio para eliminar el usuario
        ResponseEntity<Usuario> responseEntity = usuarioService.deleteUsuario(id);

        // Verificar que se haya llamado al método delete del repositorio de usuarios
        verify(usuarioRepository, times(1)).delete(usuario);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    @DisplayName("Test para intentar eliminar usuario con ID incorrecto")
    @Test
    void deleteUsuarioIdIncorrecto() {
        long id = 100L;

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> responseEntity = usuarioService.deleteUsuario(id);

        // Verificar que no se haya llamado al método delete del repositorio de usuarios
        verify(usuarioRepository, never()).delete(any(Usuario.class));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    public ResponseEntity<Usuario> getUsuarioResponseEntity(SignUpRequest usuario){
        String user="";
        for(String us: usuario.getRoles()){
            user = us;
        }
        Set<Rol> roles= new HashSet<>();
        when(rolRepository.findByNombreRol(anyString())).thenReturn(Optional.empty());
        when(rolRepository.save(any(Rol.class))).thenReturn(Rol.builder().nombreRol(user).build());
        return insertarDatos(usuario,roles);
    }
    private ResponseEntity<Usuario> insertarDatos(SignUpRequest usuario, Set<Rol> roles){
        Usuario usuarioGuardado=Usuario.builder()
                .idUsuario(1L)
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .roles(roles)
                .password(new BCryptPasswordEncoder().encode(usuario.getPassword()))
                .accountnonexpire(true)
                .credentialsnonexpired(true)
                .accountnonlocked(true)
                .enabled(true)
                .build();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }
}