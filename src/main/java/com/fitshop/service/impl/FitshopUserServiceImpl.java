package com.fitshop.service.impl;

import com.fitshop.model.custom.FitshopUser;
import com.fitshop.model.entity.UserEntity;
import com.fitshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FitshopUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found"));
    }

    private FitshopUser mapToUserDetails(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = userEntity
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRoleEnum().name()))
                        .collect(Collectors.toSet());

        return new FitshopUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities,
                userEntity.getExperienceLevel().name()
        );
    }
}
