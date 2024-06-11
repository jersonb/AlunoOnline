package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentListResponse extends ArrayList<StudentResponse> implements Serializable {
    public StudentListResponse(List<Student> students) {
        super(students.stream().map(StudentResponse::new).toList());
    }
}
