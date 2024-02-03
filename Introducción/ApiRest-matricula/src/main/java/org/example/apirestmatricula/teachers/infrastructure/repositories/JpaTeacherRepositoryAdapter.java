package org.example.apirestmatricula.teachers.infrastructure.repositories;

import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.domain.ports.out.TeacherRepositoryPort;
import org.example.apirestmatricula.teachers.infrastructure.dto.TeacherDto;
import org.example.apirestmatricula.teachers.infrastructure.entities.TeacherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaTeacherRepositoryAdapter implements TeacherRepositoryPort {
    private final JpaTeacherRepository jpaTeacherRepository;

    public JpaTeacherRepositoryAdapter(JpaTeacherRepository jpaTeacherRepository) {
        this.jpaTeacherRepository = jpaTeacherRepository;
    }

    @Override
    public Teacher save(Teacher teacher) {

        TeacherEntity teacherEntity= TeacherEntity.fromTeacherDto(TeacherDto.fromDomainModel(teacher));
        return jpaTeacherRepository.save(teacherEntity).toTeacherDto().toDomainModel();
    }

    @Override
    public List<Teacher> findAll() {
        List<TeacherEntity> teacherEntities= jpaTeacherRepository.findAll();
        return teacherEntities.stream().map(TeacherEntity::toTeacherDto).map(TeacherDto::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        Optional<TeacherEntity> teacherEntity= jpaTeacherRepository.findById(id);
        if (teacherEntity.isPresent()) {
            // Convertir el TeacherEntity a Teacher usando el método toDomainModel()
            Teacher teacher = teacherEntity.get().toTeacherDto().toDomainModel();
            return Optional.of(teacher);
        } else {
            // Si no se encuentra, devolver Optional vacío
            return Optional.empty();
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        if(jpaTeacherRepository.existsById(teacher.getId())){
            TeacherDto teacherDto= TeacherDto.fromDomainModel(teacher);
            TeacherEntity teacherEntity= TeacherEntity.fromTeacherDto(teacherDto);
            TeacherEntity updatedteacherEntity= jpaTeacherRepository.save(teacherEntity);
            return updatedteacherEntity.toTeacherDto().toDomainModel();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if(jpaTeacherRepository.existsById(id)){
            jpaTeacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
