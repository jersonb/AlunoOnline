package com.alunoonline.api.viewobjects.requests;

import com.alunoonline.api.models.RegistrationStudent;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class UpdateGradeRequest {

    @Range(max = 10, min = 0, message = "A nota deve estar entre 0 e 10.")
    Double grade1;

    @Range(max = 10, min = 0, message = "A nota deve estar entre 0 e 10.")
    Double grade2;

    public RegistrationStudent toMatriculaAluno(Long idMatriculaAluno) {
        var registrationStudent = new RegistrationStudent();
        registrationStudent.setId(idMatriculaAluno);
        registrationStudent.setGrade1(grade1);
        registrationStudent.setGrade2(grade2);
        return registrationStudent;
    }
}
