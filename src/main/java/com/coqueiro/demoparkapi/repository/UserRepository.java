package com.coqueiro.demoparkapi.repository;

import com.coqueiro.demoparkapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}