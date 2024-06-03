package com.alunoonline.api.services;

import com.alunoonline.api.models.Teacher;
import com.alunoonline.api.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public void create(Teacher teacher) {
        repository.save(teacher);
    }

    public Optional<Teacher> get(Long teacherId) {
        return repository.findById(teacherId);
    }

    public List<Teacher> get() {
        return repository.findAll();
    }

    public void delete(Long teacherId) {
        var teacher = get(teacherId);
        teacher.ifPresent(repository::delete);
    }

    public void update(Teacher teacherOld, Teacher teacher) {
        teacherOld.setName(teacher.getName());
        teacherOld.setEmail(teacher.getEmail());
        repository.save(teacherOld);
    }
}
