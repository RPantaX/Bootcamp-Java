package com.codigo.practicasonar.repository;

import com.codigo.practicasonar.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNumDocumento(String numDocumento);
}
