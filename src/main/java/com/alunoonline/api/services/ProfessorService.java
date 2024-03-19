package com.alunoonline.api.services;

import com.alunoonline.api.models.Professor;
import com.alunoonline.api.repositories.ProfessorReoiaitory;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    final ProfessorReoiaitory professorReoiaitory;

    public ProfessorService(ProfessorReoiaitory professorReoiaitory) {
        this.professorReoiaitory = professorReoiaitory;
    }

    public void crete(Professor professor) {
    }
}
