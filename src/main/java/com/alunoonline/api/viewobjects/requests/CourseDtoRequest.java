package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDtoRequest {
    @NotBlank(message = "nome precisa ser preenchido")
    String name;

    public Course toEntity() {
        var course = new Course();
        course.setName(this.name);
        return course;
    }

    public Course toEntity(Long id) {
        var course = new Course();
        course.setName(this.name);
        course.setId(id);
        return course;
    }
}
