package org.example.apirestmatricula.repositories;

import org.assertj.core.api.Assertions;
import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.domain.ports.out.TeacherRepositoryPort;
import org.example.apirestmatricula.teachers.infrastructure.entities.TeacherEntity;
import org.example.apirestmatricula.teachers.infrastructure.repositories.JpaTeacherRepository;
import org.example.apirestmatricula.teachers.infrastructure.repositories.JpaTeacherRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TeacherRepositoryTest {
    /*@InjectMocks
    private JpaTeacherRepositoryAdapter teacherRepositoryAdapter;
    @Mock
    private JpaTeacherRepository jpaTeacherRepository;


    private TeacherEntity teacherEntity;

    @BeforeEach
    void saveTeacher(){
        teacherEntity=TeacherEntity.builder()
                .name("Daniel")
                .lastName("Smith")
                .phoneNumber("123456789")
                .build();
    }
    @DisplayName("Test for save a teacher")
    @Test
    void testSaveTeacher (){
        //METH. BDD
        //given: condition before to configurations -> this we have in BeforeEach
        given(jpaTeacherRepository.save(teacherEntity)).willReturn(teacherEntity);
        //when
        Teacher teacher= teacherEntity.toDomainModel();
        Mockito.when(jpaTeacherRepository.save(Mockito.any())).thenReturn(teacher);
        Teacher savedTeacher= teacherRepositoryAdapter.save(teacher);
        //then
        Assertions.assertThat(savedTeacher).isNotNull();
        Assertions.assertThat(savedTeacher.getId()).isGreaterThan(0);
    }*/


}
