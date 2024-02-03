package org.example.apirestmatricula.courses.domain.models;


import org.example.apirestmatricula.students.domain.models.Student;
import org.example.apirestmatricula.teachers.domain.models.Teacher;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class Course implements Serializable {

    private Long id;

    private String name;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private Teacher teacher;

    private List<Student> students;

    public Course() {
    }

    public Course(Long id, String name, LocalDateTime startTime, LocalDateTime endTime, Teacher teacher, List<Student> students) {
        this.id = id;
        this.name = name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.teacher = teacher;
        this.students = students;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime(LocalDateTime endTime){
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime){
        this.endTime=endTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", teacher=" + teacher +
                ", students=" + students +
                '}';
    }
}
