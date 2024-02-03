package org.example.apirestmatricula.teachers.domain.ports.out;

import org.example.apirestmatricula.teachers.domain.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {
    Teacher save(Teacher teacher);
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher update(Teacher teacher);
    boolean deleteById(Long id);
}
