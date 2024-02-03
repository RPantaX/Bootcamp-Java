package org.example.apirestmatricula.teachers.domain.ports.in;

import org.example.apirestmatricula.teachers.domain.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherUseCases {
    Teacher createTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Long id);
    Teacher updateTeacher(Teacher teacher, Long id);
    boolean deleteTeacher(Long id);
}
