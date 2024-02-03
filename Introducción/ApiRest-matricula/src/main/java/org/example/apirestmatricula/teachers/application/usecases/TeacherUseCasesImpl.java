package org.example.apirestmatricula.teachers.application.usecases;

import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.domain.ports.in.ITeacherUseCases;
import org.example.apirestmatricula.teachers.domain.ports.out.TeacherRepositoryPort;

import java.util.List;
import java.util.Optional;

public class TeacherUseCasesImpl implements ITeacherUseCases {
    private final TeacherRepositoryPort teacherRepositoryPort;

    public TeacherUseCasesImpl(TeacherRepositoryPort teacherRepositoryPort) {
        this.teacherRepositoryPort = teacherRepositoryPort;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepositoryPort.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepositoryPort.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepositoryPort.findById(id);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher, Long id) {
        return teacherRepositoryPort.update(teacher);
    }

    @Override
    public boolean deleteTeacher(Long id) {
        return teacherRepositoryPort.deleteById(id);
    }
}
