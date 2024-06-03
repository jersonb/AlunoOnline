package com.alunoonline.api.repositories;

import com.alunoonline.api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentyRepository extends JpaRepository<Student, Long> {
}
