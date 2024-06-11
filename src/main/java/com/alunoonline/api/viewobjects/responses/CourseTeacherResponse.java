package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Course;

import java.io.Serializable;
import java.util.List;

public class CourseTeacherResponse implements Serializable {
    public final String teacher;
    public final CourseListResponse courses;

    public CourseTeacherResponse(List<Course> courses) {

        teacher = courses.get(0).getTeacher().getName();
        this.courses = new CourseListResponse(courses);

    }
}
