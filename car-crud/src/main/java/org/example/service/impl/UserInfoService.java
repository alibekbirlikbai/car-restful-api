package org.example.service.impl;

import org.example.model.auth.UserInfo;
import org.example.model.auth.UserInfoDetails;
import org.example.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public UserInfoService(UserInfoRepository repository,
                           PasswordEncoder encoder,
                           JwtService jwtService
    ) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return jwtService.generateToken(userInfo.getEmail());
    }

}