package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.RegistrationStudent;

public class RegistrationStudentResponse {
    public final Long id;
    public final String name;
    public final String email;
    public final String status;
    public final Double grade1;
    public final Double grade2;

    public RegistrationStudentResponse(RegistrationStudent registrationStudent) {
        this.id = registrationStudent.getStudent().getId();
        this.name = registrationStudent.getStudent().getName();
        this.email = registrationStudent.getStudent().getEmail();
        this.status = registrationStudent.getRegistrationStudentStatusEnum().name();
        this.grade1 = registrationStudent.getGrade1();
        this.grade2 = registrationStudent.getGrade2();

    }
}
