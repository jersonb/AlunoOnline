package com.alunoonline.api.repositories;

import com.alunoonline.api.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    @Query("select count(u) > 0 from User u where u.email = :email and u.password = :password")
    boolean findByLoginAndPassword(String email, String password);
}
