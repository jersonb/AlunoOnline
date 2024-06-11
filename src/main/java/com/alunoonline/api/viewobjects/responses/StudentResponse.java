package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.Student;

import java.io.Serializable;

public class StudentResponse implements Serializable {
    public final Long id;
    public final String name;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
    }
}
