package org.example.controller;

import org.example.model.auth.UserInfo;
import org.example.service.impl.UserInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserInfoService service;

    public AuthController(UserInfoService service) {
        this.service = service;
    }

    @PostMapping("/sign-in")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }
}