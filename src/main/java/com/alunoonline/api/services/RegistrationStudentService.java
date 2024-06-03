package com.alunoonline.api.services;

import com.alunoonline.api.enums.RegistrationStudentStatusEnum;
import com.alunoonline.api.models.RegistrationStudent;

import com.alunoonline.api.repositories.MatriculaAlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationStudentService {

    final MatriculaAlunoRepository repository;

    public RegistrationStudentService(MatriculaAlunoRepository repository) {
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

    public void update(RegistrationStudent matriculaAlunoOld, RegistrationStudent matriculaAluno) {
        matriculaAlunoOld.setStudent(matriculaAluno.getStudent());
        matriculaAlunoOld.setCourse(matriculaAluno.getCourse());
        matriculaAlunoOld.setNota1(matriculaAluno.getNota1());
        matriculaAlunoOld.setNota2(matriculaAluno.getNota2());
        matriculaAlunoOld.setRegistrationStudentStatusEnum(matriculaAluno.getRegistrationStudentStatusEnum());
        repository.save(matriculaAlunoOld);
    }
}
