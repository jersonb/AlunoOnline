package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Teacher;

import java.io.Serializable;


public class TeacherResponse implements Serializable {

    public final Long id;
    public final String name;
    public final String email;
    public final CourseListResponse courses;

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.courses = new CourseListResponse(teacher.getCourses().stream().toList());
    }
}
