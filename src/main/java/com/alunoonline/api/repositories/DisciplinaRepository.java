package com.alunoonline.api.repositories;

import com.alunoonline.api.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
    List<Disciplina> findByProfessor(Long id);
}
