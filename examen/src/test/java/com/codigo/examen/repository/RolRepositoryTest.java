package com.codigo.examen.repository;

import com.codigo.examen.entity.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
public class RolRepositoryTest {
    @Autowired
    private RolRepository rolRepository;
    private Rol rol;
    @BeforeEach
    void guardarRoles()
    {
        rol = Rol.builder().nombreRol("ADMIN").build();
    }
    @DisplayName("Test para buscar un rol por su nombre")
    @Test
    void testBuscarRolPorNombreRol(){
        rolRepository.save(rol);

        Optional<Rol> rolBD = rolRepository.findByNombreRol(rol.getNombreRol());

        assertThat(rolBD).isNotEmpty();
        assertThat(rolBD.get().getNombreRol()).isEqualTo("ADMIN");
    }
    @DisplayName("Buscar un rol por su nombre cuando no se encuentra")
    @Test
    void testBuscarRolPorNombreRolNoEncontrado() {
        Optional<Rol> rolBD = rolRepository.findByNombreRol("ROL_NO_EXISTENTE");

        assertThat(rolBD).isEmpty();
    }

    @DisplayName("Guardar un nuevo rol")
    @Test
    void testGuardarNuevoRol() {
        Rol rolGuardado= rolRepository.save(rol);

        assertThat(rolGuardado).isNotNull();
        assertThat(rolGuardado.getNombreRol()).isEqualTo("ADMIN");
    }

}
