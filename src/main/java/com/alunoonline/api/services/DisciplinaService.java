package com.alunoonline.api.services;

import com.alunoonline.api.models.Disciplina;
import com.alunoonline.api.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {
    final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public void create(Disciplina disciplina) {
        repository.save(disciplina);
    }

    public Optional<Disciplina> get(Long disciplinaId) {
        return repository.findById(disciplinaId);
    }

    public List<Disciplina> get() {
        return repository.findAll();
    }

    public void delete(Long disciplinaId) {
        var disciplina = get(disciplinaId);
        disciplina.ifPresent(repository::delete);
    }

    public void update(Disciplina disciplinaOld, Disciplina disciplina) {
        disciplinaOld.setNome(disciplina.getNome());
        repository.save(disciplinaOld);
    }

    public  List<Disciplina> getDisciplinaByProfessor(Long professorId){
        return  repository.findByProfessor(professorId);
    }

}
