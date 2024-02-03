package org.example.apirestmatricula.teachers.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import org.example.apirestmatricula.courses.domain.models.Course;
import org.example.apirestmatricula.courses.infrastructure.entities.CourseEntity;
import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.infrastructure.dto.TeacherDto;
import org.w3c.dom.css.Counter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@Builder
public class TeacherEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;
    private String phoneNumber;
    @JsonBackReference //we avoid the error by json
    @OneToMany(mappedBy = "teacherEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courses = new ArrayList<>();

    public TeacherEntity() {
    }

    public TeacherEntity(Long id, String name, String lastName, String phoneNumber, List<CourseEntity> courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }
    public TeacherEntity(Long id, String name, String lastName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public static TeacherEntity fromTeacherDto(TeacherDto teacherDto) {
        return new TeacherEntity(teacherDto.getId(), teacherDto.getName(), teacherDto.getLastName(), teacherDto.getPhoneNumber());
    }
    public TeacherDto toTeacherDto(){
        return new TeacherDto(id, name, lastName, phoneNumber);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courses=" + courses +
                '}';
    }
}
