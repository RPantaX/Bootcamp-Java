package org.example.apirestmatricula.teachers.domain.models;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.apirestmatricula.courses.domain.models.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public class Teacher implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private List<Course> courses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(Long id, String name, String lastName, String phoneNumber, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }
    public Teacher(Long id, String name, String lastName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
}
