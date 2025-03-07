package com.dOliveira.feedback_service.service;

import com.dOliveira.feedback_service.dto.UserDTO;
import com.dOliveira.feedback_service.entity.User;
import com.dOliveira.feedback_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {

        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()));
    }

    public Optional<UserDTO> getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()));
    }

    public UserDTO createUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public Optional<UserDTO> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            User savedUser = userRepository.save(existingUser);
            return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
        });
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
