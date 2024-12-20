package com.spring.controller;

import com.spring.model.dto.AuthenticationRequest;
import com.spring.model.dto.AuthenticationResponse;
import com.spring.model.dto.UserDto;
import com.spring.model.entity.User;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.service.AuthenticationService;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserService userServices;
    private final EntityDtoMapper entityDtoMapper;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        for (UserDto users : userServices.findAll()) {
            if (users.getUsername().equals(user.getUsername())) {
                throw new RuntimeException("This username ( " + user.getUsername() + " ) is exist");
            } else if (users.getEmail().equals(user.getEmail()))
                throw new RuntimeException("This email ( " + user.getEmail() + " ) is exist");
        }
        userServices.insert(entityDtoMapper.userToUserDto(user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
