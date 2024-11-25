package com.alunoonline.api.repositories;

import com.alunoonline.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(u) > 0 from User u where u.email = :email")
    boolean findByEmail(String email);

    @Query("select count(u) > 0 from User u where u.email = :email and u.password = :password")
    boolean findByLoginAndPassword(String email, String password);
}
