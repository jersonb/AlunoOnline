package com.alunoonline.api.services;

import com.alunoonline.api.enums.RegistrationStudentStatusEnum;
import com.alunoonline.api.models.RegistrationStudent;

import com.alunoonline.api.repositories.RegistrationStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationStudentService {

    final RegistrationStudentRepository repository;

    public RegistrationStudentService(RegistrationStudentRepository repository) {
        this.repository = repository;
    }

    public void create(RegistrationStudent registrationStudent) {
        registrationStudent.setRegistrationStudentStatusEnum(RegistrationStudentStatusEnum.REGISTERED);
        repository.save(registrationStudent);
    }

    public Optional<RegistrationStudent> get(Long registrationStudentId) {
        return repository.findById(registrationStudentId);
    }

    public List<RegistrationStudent> get() {
        return repository.findAll();
    }

    public void delete(Long registrationStudentId) {
        var registrationStudent = get(registrationStudentId);
        registrationStudent.ifPresent(repository::delete);
    }

    public void update(RegistrationStudent registrationStudentOld, RegistrationStudent registrationStudent) {
        registrationStudentOld.setStudent(registrationStudent.getStudent());
        registrationStudentOld.setCourse(registrationStudent.getCourse());
        registrationStudentOld.setGrade1(registrationStudent.getGrade1());
        registrationStudentOld.setGrade2(registrationStudent.getGrade2());
        registrationStudentOld.setRegistrationStudentStatusEnum(registrationStudent.getRegistrationStudentStatusEnum());
        repository.save(registrationStudentOld);
    }
}
