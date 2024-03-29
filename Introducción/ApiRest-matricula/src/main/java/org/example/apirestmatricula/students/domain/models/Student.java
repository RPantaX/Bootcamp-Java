package org.example.apirestmatricula.students.domain.models;

import org.example.apirestmatricula.courses.domain.models.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Student implements Serializable {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String phoneNumber;

    private List<Course> courses= new ArrayList<>();

    public Student() {
    }

    public Student(Long id, String name, String lastName, String email, String phoneNumber, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courses=" + courses +
                '}';
    }
}
