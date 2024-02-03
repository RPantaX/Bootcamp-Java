package org.example.apirestmatricula.teachers.infrastructure.repositories;

import org.example.apirestmatricula.teachers.infrastructure.entities.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
