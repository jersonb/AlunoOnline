package com.alunoonline.api.repositories;

import com.alunoonline.api.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorReoiaitory extends JpaRepository<Professor, Long> {
}
