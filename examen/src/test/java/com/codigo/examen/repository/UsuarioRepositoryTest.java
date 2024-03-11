package com.codigo.examen.repository;

import com.codigo.examen.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @BeforeEach
    void guardarUsuario(){
        usuario=Usuario.builder()
                .username("Jefferson")
                .email("jeff@gmail.com")
                .telefono("987654321")
                .password("123456")
                .build();
    }
    @DisplayName("Test para guardar un usuario")
    @Test
    void testGuardadUsuario(){
        //metodologia BDD
        //given - dado o condición previa a configuración - Dado este usuario
        Usuario user1= Usuario.builder()
                .username("Pepe")
                .email("p12@gmail.com")
                .telefono("930889076")
                .password("pepe123")
                .build();
        //when - acción o el comportamiento que vamos a probar - cuando llamemos al repositorio y guardemos
        Usuario usuarioGuardado= usuarioRepository.save(user1);
        //then - verificar la salida -
        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getIdUsuario()).isGreaterThan(0); //esperamos que no sea nulo y su id sea mayor a 0
    }
    @DisplayName("Test para listar a los usuarios")
    @Test
    void testListarUsuarios(){
        //BDD
        //given
        Usuario user1= Usuario.builder()
                .username("Julen")
                .email("julen@gmail.com")
                .telefono("987654321")
                .password("123456")
                .build();
        usuarioRepository.save(user1);
        usuarioRepository.save(usuario);
        //when
        List<Usuario> empleados= usuarioRepository.findAll();
        //THEN
        assertThat(empleados).isNotNull();
        assertThat(empleados.size()).isEqualTo(2);
    }
    @DisplayName("Listar usuarios por ID")
    @Test
    void testObtenerUsuarioPorId(){
        //given
        usuarioRepository.save(usuario);
        //when
        Usuario usuario1= usuarioRepository.findById(usuario.getIdUsuario()).get();
        //then
        assertThat(usuario1).isNotNull();
        assertThat(usuario1.getEmail()).isEqualTo("jeff@gmail.com");
    }
    @DisplayName("Listar usuarios por username")
    @Test
    void testObtenerUsuarioPorUsername(){
        //given
        usuarioRepository.save(usuario);
        //when
        Usuario usuario1= usuarioRepository.findByUsername(usuario.getUsername()).get();
        //then
        assertThat(usuario1).isNotNull();
        assertThat(usuario1.getUsername()).isEqualTo("Jefferson");
        assertThat(usuario1.getEmail()).isEqualTo("jeff@gmail.com");
    }
    @DisplayName("Test para actualizar un usuario")
    @Test
    void testActualizarUsuario(){
        //given
        usuarioRepository.save(usuario);
        //when
        Usuario usuarioGuardado= usuarioRepository.findById(usuario.getIdUsuario()).get();
        usuarioGuardado.setEmail("pantajefferson123@gmail.com");
        usuarioGuardado.setUsername("Roberto");
        usuarioGuardado.setTelefono("942066952");
        Usuario usuarioActualizado= usuarioRepository.save(usuarioGuardado);
        //then
        assertThat(usuarioActualizado).isNotNull();
        assertThat(usuarioActualizado.getIdUsuario()).isEqualTo(2L);
        assertThat(usuarioActualizado.getEmail()).isEqualTo("pantajefferson123@gmail.com");
        assertThat(usuarioActualizado.getUsername()).isEqualTo("Roberto");
        assertThat(usuarioActualizado.getTelefono()).isEqualTo("942066952");
    }
    @DisplayName("Test para eliminar un usuario")
    @Test
    void testEliminarUsuarios(){
        //given
        usuarioRepository.save(usuario);
        //WHEN
        usuarioRepository.delete(usuario);
        Optional<Usuario> usuarioEliminado= usuarioRepository.findById(usuario.getIdUsuario()); //true si lo encuentra, false si no lo encuentra
        //THEN
        assertThat(usuarioEliminado).isEmpty();
    }
}
