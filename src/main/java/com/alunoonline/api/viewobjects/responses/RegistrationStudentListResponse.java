package com.alunoonline.api.viewobjects.responses;

import com.alunoonline.api.models.RegistrationStudent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrationStudentListResponse extends ArrayList<RegistrationStudentResponse> implements Serializable {
    public RegistrationStudentListResponse(List<RegistrationStudent> registrations) {
        super(registrations.stream().map(RegistrationStudentResponse::new).toList());
    }
}
