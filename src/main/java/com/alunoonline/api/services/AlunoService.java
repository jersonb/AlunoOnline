package com.alunoonline.api.services;

import com.alunoonline.api.models.Aluno;
import com.alunoonline.api.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    final AlunoRepository repository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.repository = alunoRepository;
    }

    public void crete(Aluno aluno) {
        repository.save(aluno);
    }

    public Optional<Aluno> get(Long alunoId) {
        return repository.findById(alunoId);
    }

    public List<Aluno> get() {
        return repository.findAll();
    }

    public void delete(Long alunoId) {
        var aluno = get(alunoId);
        aluno.ifPresent(repository::delete);
    }
}
