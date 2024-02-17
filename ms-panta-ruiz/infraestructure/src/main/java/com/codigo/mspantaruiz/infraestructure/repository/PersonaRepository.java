package com.codigo.mspantaruiz.infraestructure.repository;

import com.codigo.mspantaruiz.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByNumDocu(@Param("numDocu") String numDocu);
    List<PersonaEntity> findAllByEstado(Integer estado);
}
