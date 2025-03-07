package com.dOliveira.feedback_service.service;

import com.dOliveira.feedback_service.dto.LoginDTO;
import com.dOliveira.feedback_service.entity.User;
import com.dOliveira.feedback_service.repository.UserRepository;
import com.dOliveira.feedback_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public String authenticate(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.getEmail());

        if (userOpt.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), userOpt.get().getPassword())){
            return jwtUtil.generateToken(userOpt.get());
        }
        throw new RuntimeException("Credenciais inválidas!");
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash da senha
        return userRepository.save(user);
    }
}
