package com.spring.service;

import com.spring.model.dto.UserDto;
import com.spring.model.entity.User;
import com.spring.model.mapper.EntityDtoMapper;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, EntityDtoMapper entityDtoMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return entityDtoMapper.userToUserDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(entityDtoMapper::userToUserDto).toList();
    }

    public UserDto insert(UserDto userDto) {
        User user = entityDtoMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return entityDtoMapper.userToUserDto(user);
    }

    public UserDto updateUser(int userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setRole(User.Role.valueOf(userDto.getRole()));
        user = userRepository.save(user);
        return entityDtoMapper.userToUserDto(user);
    }

    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}