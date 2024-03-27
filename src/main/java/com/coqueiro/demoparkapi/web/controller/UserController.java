package com.coqueiro.demoparkapi.web.controller;

import com.coqueiro.demoparkapi.entity.User;
import com.coqueiro.demoparkapi.service.UserService;
import com.coqueiro.demoparkapi.web.dto.UserCreateDto;
import com.coqueiro.demoparkapi.web.dto.UserPasswordDto;
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
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDto dto) {
        User updatedUser = userService.changePassword(id, dto.getActualPassword(), dto.getNewPassword(), dto.getPasswordConfirmation());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDto(users));
    }
}
