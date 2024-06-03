package com.alunoonline.api.repositories;

import com.alunoonline.api.models.RegistrationStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<RegistrationStudent,Long> {
}
