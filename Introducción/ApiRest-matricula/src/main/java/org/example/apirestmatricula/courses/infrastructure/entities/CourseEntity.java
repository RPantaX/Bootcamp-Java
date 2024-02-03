package org.example.apirestmatricula.courses.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Builder;
import org.example.apirestmatricula.students.domain.models.Student;
import org.example.apirestmatricula.students.infrastructure.entities.StudentsEntity;
import org.example.apirestmatricula.teachers.domain.models.Teacher;
import org.example.apirestmatricula.teachers.infrastructure.entities.TeacherEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
@Builder
public class CourseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)

    private LocalDateTime startTime;
    @Column(nullable = false)

    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY) //lazy loading
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity teacherEntity;
    @ManyToMany
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    )
    private List<StudentsEntity> students;

    public CourseEntity() {
    }

    public CourseEntity(Long id, String name, LocalDateTime startTime, LocalDateTime endTime, TeacherEntity teacherEntity, List<StudentsEntity> students) {
        this.id = id;
        this.name = name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.teacherEntity = teacherEntity;
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
    public LocalDateTime getEndTime(){
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime){
        this.endTime=endTime;
    }

    public TeacherEntity getTeacher() {
        return teacherEntity;
    }

    public void setTeacher(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public List<StudentsEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentsEntity> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", teacher=" + teacherEntity +
                ", students=" + students +
                '}';
    }
}
