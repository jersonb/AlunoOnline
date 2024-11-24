package com.alunoonline.api.repositories;

import com.alunoonline.api.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
