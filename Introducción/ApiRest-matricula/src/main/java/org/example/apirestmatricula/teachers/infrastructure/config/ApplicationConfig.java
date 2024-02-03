package org.example.apirestmatricula.teachers.infrastructure.config;

import org.example.apirestmatricula.teachers.application.services.TeacherService;
import org.example.apirestmatricula.teachers.application.usecases.TeacherUseCasesImpl;
import org.example.apirestmatricula.teachers.domain.ports.out.TeacherRepositoryPort;
import org.example.apirestmatricula.teachers.infrastructure.repositories.JpaTeacherRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public TeacherRepositoryPort teacherRepositoryPort(JpaTeacherRepositoryAdapter jpaTaskRepositoryAdapter){
        return jpaTaskRepositoryAdapter;
    }
    @Bean
    public TeacherService teacherService(TeacherRepositoryPort teacherRepositoryPort){
        return new TeacherService(new TeacherUseCasesImpl(teacherRepositoryPort));
    }
}
