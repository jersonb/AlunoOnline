package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank(message = "nome precisa ser preenchido")
    String name;

    public Course toEntity() {
        return this.toEntity(0L);
    }

    public Course toEntity(Long id) {
        var course = new Course();
        course.setName(this.name);
        course.setId(id);
        return course;
    }
}
