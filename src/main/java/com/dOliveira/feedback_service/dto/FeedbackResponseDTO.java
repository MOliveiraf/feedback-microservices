package com.dOliveira.feedback_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackResponseDTO {
    private Long id;
    private Long feedbackId;
    private Long userId;
    private String message;
    private LocalDateTime createdAt;
}

