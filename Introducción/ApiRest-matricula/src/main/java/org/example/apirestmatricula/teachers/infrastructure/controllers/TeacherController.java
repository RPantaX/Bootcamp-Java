package org.example.apirestmatricula.teachers.infrastructure.controllers;

import jakarta.validation.Valid;
import org.example.apirestmatricula.teachers.application.services.TeacherService;
import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.infrastructure.dto.TeacherDto;
import org.example.apirestmatricula.teachers.infrastructure.entities.TeacherEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers(){
        List<TeacherDto> teachersDto= teacherService.getAllTeachers().stream().map(TeacherDto::fromDomainModel).toList();
        return new ResponseEntity<>(teachersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById( @PathVariable Long id){
        return teacherService.getTeacherById(id)
                .map(teacher -> new ResponseEntity<>(TeacherDto.fromDomainModel(teacher), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody TeacherDto teacherDto){
        TeacherDto teacherDto1=TeacherDto.fromDomainModel(teacherService.createTeacher(teacherDto.toDomainModel()));
        return new ResponseEntity<>(teacherDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@Valid @RequestBody TeacherDto teacherDto, @PathVariable Long id){
        return new ResponseEntity<>(teacherService.updateTeacher(teacherDto.toDomainModel(), id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        if(teacherService.deleteTeacher(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
