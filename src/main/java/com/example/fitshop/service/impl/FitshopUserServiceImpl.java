package com.example.fitshop.service.impl;

import com.example.fitshop.model.entity.UserEntity;
import com.example.fitshop.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FitshopUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public FitshopUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(this::mapToUserDetails)
                .orElse(null);
    }

    private UserDetails mapToUserDetails(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities =

                userEntity
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleEnum().name()))
                        .collect(Collectors.toSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );

    }


}