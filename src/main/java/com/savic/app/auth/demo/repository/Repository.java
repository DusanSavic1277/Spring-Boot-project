package com.savic.app.auth.demo.repository;

import com.savic.app.auth.demo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);
    
}