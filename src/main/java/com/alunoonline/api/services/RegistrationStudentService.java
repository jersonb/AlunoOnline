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

    public void create(RegistrationStudent matriculaAluno) {
        matriculaAluno.setRegistrationStudentStatusEnum(RegistrationStudentStatusEnum.REGISTERED);
        repository.save(matriculaAluno);
    }

    public Optional<RegistrationStudent> get(Long matriculaAlunoId) {
        return repository.findById(matriculaAlunoId);
    }

    public List<RegistrationStudent> get() {
        return repository.findAll();
    }

    public void delete(Long matriculaAlunoId) {
        var matriculaAluno = get(matriculaAlunoId);
        matriculaAluno.ifPresent(repository::delete);
    }

    public void update(RegistrationStudent registrationStudentOld, RegistrationStudent registrationStudent) {
        registrationStudentOld.setStudent(registrationStudent.getStudent());
        registrationStudentOld.setCourse(registrationStudent.getCourse());
        registrationStudentOld.setNota1(registrationStudent.getNota1());
        registrationStudentOld.setNota2(registrationStudent.getNota2());
        registrationStudentOld.setRegistrationStudentStatusEnum(registrationStudent.getRegistrationStudentStatusEnum());
        repository.save(registrationStudentOld);
    }
}
