package com.alunoonline.api.repositories;

import com.alunoonline.api.models.RegistrationStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationStudentRepository extends JpaRepository<RegistrationStudent, Long> {
    List<RegistrationStudent> findByStudentId(Long id);
}
