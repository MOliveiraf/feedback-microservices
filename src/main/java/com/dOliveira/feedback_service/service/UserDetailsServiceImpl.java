package com.dOliveira.feedback_service.service;

import com.dOliveira.feedback_service.entity.User;
import com.dOliveira.feedback_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        User user = userOpt.get();
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword()) // A senha já está encriptada no banco
                .roles(user.getRole().name().replace("ROLE_", "")) // Remove "ROLE_" para compatibilidade
                .build();
    }
}
