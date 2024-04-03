package com.alunoonline.api.services;

import com.alunoonline.api.models.Professor;
import com.alunoonline.api.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public void create(Professor professor) {
        repository.save(professor);
    }

    public Optional<Professor> get(Long professorId) {
        return  repository.findById(professorId);
    }

    public List<Professor> get() {
       return repository.findAll();
    }

    public void delete(Long professorId) {
        var professor = get(professorId);
        professor.ifPresent(repository::delete);
    }

    public  void update(Professor professorOld, Professor professor){
        professorOld.setName(professor.getName());
        professorOld.setEmail(professor.getEmail());
        repository.save(professorOld);
    }
}
