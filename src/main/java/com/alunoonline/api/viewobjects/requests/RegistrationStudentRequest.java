package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Student;
import com.alunoonline.api.models.Course;
import com.alunoonline.api.models.RegistrationStudent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationStudentRequest {
    @NotEmpty
    @Min(value = 1, message = "id inválido")
    Long studentId;

    @NotEmpty
    @Min(value = 1, message = "id inválido")
    Long courseId;

    public RegistrationStudent toEntity() {
        return this.toEntity(0L);
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
