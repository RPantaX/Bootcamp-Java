package org.example.apirestmatricula.teachers.application.services;

import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.domain.ports.in.ITeacherUseCases;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class TeacherService implements ITeacherUseCases {
    private final ITeacherUseCases teacherUseCases;

    public TeacherService(ITeacherUseCases teacherUseCases) {
        this.teacherUseCases = teacherUseCases;
    }

    @Transactional
    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherUseCases.createTeacher(teacher);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherUseCases.getAllTeachers();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherUseCases.getTeacherById(id);
    }
    @Transactional
    @Override
    public Teacher updateTeacher(Teacher teacher, Long id) {
        return teacherUseCases.updateTeacher(teacher, id);
    }
    @Transactional
    @Override
    public boolean deleteTeacher(Long id) {
        return teacherUseCases.deleteTeacher(id);
    }
}
