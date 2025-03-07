package com.dOliveira.feedback_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") // Define o nome da tabela no banco
@Getter  // Criada com lombok
@Setter
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os campos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento no banco
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) // O email deve ser único
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Define o papel do usuário (ADMIN ou USER)

    public enum Role {
        ROLE_USER,
        ROLE_ADMIN
    }

}
