package com.alunoonline.api.services;


import com.alunoonline.api.models.UserInfo;
import com.alunoonline.api.models.UserInfoDetails;
import com.alunoonline.api.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userDetail = repository.findByEmail(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public boolean addUser(UserInfo userInfo) {

        var alreadyExists = repository.existsByEmail(userInfo.getEmail());
        if (alreadyExists){
            return false;
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return true;
    }
}
