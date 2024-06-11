package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Teacher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeacherListResponse extends ArrayList<TeacherResponse> implements Serializable {
    public TeacherListResponse(List<Teacher> teachers) {
        super(teachers.stream().map(TeacherResponse::new).toList());
    }
}
