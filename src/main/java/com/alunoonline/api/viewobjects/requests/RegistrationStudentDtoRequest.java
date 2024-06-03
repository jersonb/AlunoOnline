package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Student;
import com.alunoonline.api.models.Course;
import com.alunoonline.api.models.RegistrationStudent;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RegistrationStudentDtoRequest {
    @Min(value = 1, message = "id inválido")
    Long studentId;
    @Min(value = 1, message = "id inválido")
    Long courseId;

    public RegistrationStudent toEntity() {
        var registrationStudent = new RegistrationStudent();
        var student = new Student();
        student.setId(studentId);
        var course = new Course();
        course.setId(courseId);
        registrationStudent.setStudent(student);
        registrationStudent.setCourse(course);
        return registrationStudent;
    }

    public RegistrationStudent toEntity(Long id) {
        var registrationStudent = new RegistrationStudent();
        var student = new Student();
        student.setId(studentId);
        var course = new Course();
        course.setId(courseId);
        registrationStudent.setStudent(student);
        registrationStudent.setCourse(course);
        registrationStudent.setId(id);
        return registrationStudent;
    }
}
