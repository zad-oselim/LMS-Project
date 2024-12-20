package com.spring.db;

import com.spring.model.dto.UserDto;
import com.spring.model.entity.User;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartUp implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            UserDto user = UserDto.builder()
                    .username("master")
                    .password("master")
                    .fullName("master")
                    .email("master@mail.com")
                    .role("ADMIN")
                    .build();
            userService.insert(user);
        }


    }
}
