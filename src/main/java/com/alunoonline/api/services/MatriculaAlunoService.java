package com.alunoonline.api.services;

import com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import com.alunoonline.api.models.MatriculaAluno;

import com.alunoonline.api.repositories.MatriculaAlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaAlunoService {

    final MatriculaAlunoRepository repository;

    public MatriculaAlunoService(MatriculaAlunoRepository repository) {
        this.repository = repository;
    }

    public void create(MatriculaAluno matriculaAluno) {
        matriculaAluno.setMatriculaAlunoStatusEnum(MatriculaAlunoStatusEnum.MATRICULADO);
        repository.save(matriculaAluno);
    }

    public Optional<MatriculaAluno> get(Long matriculaAlunoId) {
        return repository.findById(matriculaAlunoId);
    }

    public List<MatriculaAluno> get() {
        return repository.findAll();
    }

    public void delete(Long matriculaAlunoId) {
        var matriculaAluno = get(matriculaAlunoId);
        matriculaAluno.ifPresent(repository::delete);
    }

    public void update(MatriculaAluno matriculaAlunoOld, MatriculaAluno matriculaAluno) {
        matriculaAlunoOld.setAluno(matriculaAluno.getAluno());
        matriculaAlunoOld.setDisciplina(matriculaAluno.getDisciplina());
        matriculaAlunoOld.setNota1(matriculaAluno.getNota1());
        matriculaAlunoOld.setNota2(matriculaAluno.getNota2());
        matriculaAlunoOld.setMatriculaAlunoStatusEnum(matriculaAluno.getMatriculaAlunoStatusEnum());
        repository.save(matriculaAlunoOld);
    }
}
