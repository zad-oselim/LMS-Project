package com.spring.controller;


import com.spring.model.dto.UserDto;
import com.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserService() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("testUser");

        when(userService.getUserById(1)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertThat(result.getUsername()).isEqualTo("testUser");
        verify(userService, times(1)).getUserById(1);
    }

    @TestConfiguration
    static class MockConfiguration {

        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }   }