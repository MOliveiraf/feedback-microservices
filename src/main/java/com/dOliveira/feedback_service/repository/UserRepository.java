package com.dOliveira.feedback_service.repository;

import com.dOliveira.feedback_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Método para criar usuário pelo email
}
