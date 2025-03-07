package com.dOliveira.feedback_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbackResponses")
@Getter
@Setter
@NoArgsConstructor
public class FeedbackResponse {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long feedbackId; // ID do feedback ao qual esta resposta pertence

    @Column(nullable = false)
    private Long userId; // ID do usuário que respondeu

    @Column(nullable = false, length = 500)
    private String message; // Conteúdo da resposta

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
