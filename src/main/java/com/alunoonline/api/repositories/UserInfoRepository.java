package com.alunoonline.api.repositories;

import com.alunoonline.api.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    @Query("select count(u) = 1  from UserInfo u where u.email = :email")
    boolean existsByEmail(String email);
}
