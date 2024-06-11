package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Course;

import java.io.Serializable;

public class CourseResponse implements Serializable {
    public final Long id;
    public final String name;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
    }

}
