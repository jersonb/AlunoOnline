package com.alunoonline.api.services;

import com.alunoonline.api.models.Student;
import com.alunoonline.api.repositories.StudentyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final StudentyRepository repository;

    public StudentService(StudentyRepository repository) {
        this.repository = repository;
    }

    public void create(Student student) {
        repository.save(student);
    }

    public Optional<Student> get(Long studentId) {
        return repository.findById(studentId);
    }

    public List<Student> get() {
        return repository.findAll();
    }

    public void delete(Long studentId) {
        var student = get(studentId);
        student.ifPresent(repository::delete);
    }

    public void update(Student studentOld, Student student) {
        studentOld.setName(student.getName());
        studentOld.setEmail(student.getEmail());

        repository.save(studentOld);
    }
}
