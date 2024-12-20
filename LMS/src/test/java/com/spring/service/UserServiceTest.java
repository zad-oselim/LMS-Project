package com.spring.service;

import com.spring.model.dto.UserDto;
import com.spring.model.entity.User;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityDtoMapper entityDtoMapper;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_UserExists() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");

        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("testUser");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(entityDtoMapper.userToUserDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(1);
    }
}
