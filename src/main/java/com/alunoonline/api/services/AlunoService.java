package com.alunoonline.api.services;

import com.alunoonline.api.models.Aluno;
import com.alunoonline.api.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public  void crete (Aluno aluno){
        alunoRepository.save(aluno);
    }

    public Optional<Aluno> get(Long alunoId) {
        return alunoRepository.findById(alunoId);
    }
}
