package org.example.apirestmatricula.teachers.infrastructure.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.apirestmatricula.courses.domain.models.Course;
import org.example.apirestmatricula.teachers.domain.models.Teacher;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public class TeacherDto implements Serializable {
    @NotNull(message = "The given id must not be null")
    private Long id;
    @NotEmpty(message = "the last name cannot be empty")
    @Size(min = 2, message = "the name must have at least 2 characters")
    private String name;
    @NotEmpty(message = "the last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "the last name cannot be empty")
    @Size(max = 9, min = 9,message = "the cell phone number must be 9 digits long")
    private String phoneNumber;
    private List<Course> courses = new ArrayList<>();

    public TeacherDto() {
    }

    public TeacherDto(Long id, String name, String lastName, String phoneNumber, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }
    public TeacherDto(Long id, String name, String lastName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public static TeacherDto fromDomainModel(Teacher teacher) {
        return new TeacherDto(teacher.getId(), teacher.getName(), teacher.getLastName(), teacher.getPhoneNumber());
    }
    public Teacher toDomainModel(){
        return new Teacher(id, name, lastName, phoneNumber);
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
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
