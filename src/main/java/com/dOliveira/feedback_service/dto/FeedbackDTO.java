package com.dOliveira.feedback_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackDTO {
    private Long id;
    private Long userId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
}
