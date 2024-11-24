package com.alunoonline.api.services;

import com.alunoonline.api.models.Course;
import com.alunoonline.api.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void create(Course course) {
        repository.save(course);
    }

    public Optional<Course> get(Long courseId) {
        return repository.findById(courseId);
    }

    public List<Course> get() {
        return repository.findAll();
    }

    public void delete(Long courseId) {
        var course = get(courseId);
        course.ifPresent(repository::delete);
    }

    public void update(Course courseOld, Course course) {
        courseOld.setName(course.getName());
        repository.save(courseOld);
    }

}
