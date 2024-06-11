package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseListResponse extends ArrayList<CourseResponse> implements Serializable {

    public CourseListResponse(List<Course> courses) {
        super(courses.stream().map(CourseResponse::new).toList());
    }
}
