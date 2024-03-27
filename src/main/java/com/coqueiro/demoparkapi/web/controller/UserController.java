package com.coqueiro.demoparkapi.web.controller;

import com.coqueiro.demoparkapi.entity.User;
import com.coqueiro.demoparkapi.service.UserService;
import com.coqueiro.demoparkapi.web.dto.UserCreateDto;
import com.coqueiro.demoparkapi.web.dto.UserResponseDto;
import com.coqueiro.demoparkapi.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto createDto) {
        User createdUser = userService.save(UserMapper.toUser(createDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.changePassword(id, user.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
