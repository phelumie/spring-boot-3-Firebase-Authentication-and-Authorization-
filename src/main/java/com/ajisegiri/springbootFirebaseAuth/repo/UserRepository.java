package com.ajisegiri.springbootFirebaseAuth.repo;

import com.ajisegiri.springbootFirebaseAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
