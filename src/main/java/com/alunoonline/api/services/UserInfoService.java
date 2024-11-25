package com.alunoonline.api.services;

import com.alunoonline.api.configs.JwtService;
import com.alunoonline.api.models.UserInfo;
import com.alunoonline.api.repositories.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {
    final UserInfoRepository repository;
    final JwtService jwtTokenService;

    public UserService(UserInfoRepository repository, JwtService jwtTokenService) {
        this.repository = repository;
        this.jwtTokenService = jwtTokenService;
    }

    public boolean create(UserInfo user) {
        var userExists = repository.findByEmail(user.getEmail());
        if (userExists.isEmpty()) {
            return false;
        }
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        repository.save(user);
        return true;
    }

    public String login(UserInfo user) {
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        var userExists = repository.findByLoginAndPassword(user.getEmail(), user.getPassword());
        if (userExists) {
            return jwtTokenService.generateToken(user.getEmail());
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
