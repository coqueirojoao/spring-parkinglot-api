package com.coqueiro.demoparkapi.service;

import com.coqueiro.demoparkapi.entity.User;
import com.coqueiro.demoparkapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado.")
        );
    }

    @Transactional
    public User changePassword(Long id, String actualPassword, String newPassword, String passwordConfirmation) {
        if (!newPassword.equals(passwordConfirmation)) {
            throw new RuntimeException("Nova senha não confere com confirmação de senha.");
        }

        User user = findById(id);

        if (!user.getPassword().equals(actualPassword)) {
            throw new RuntimeException("Sua senha não confere.");
        }

        user.setPassword(newPassword);

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
