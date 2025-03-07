package com.dOliveira.feedback_service.controller;


import com.dOliveira.feedback_service.dto.FeedbackDTO;
import com.dOliveira.feedback_service.entity.Feedback;
import com.dOliveira.feedback_service.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedback));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<FeedbackDTO> approveFeedback(@PathVariable Long id) {
        return feedbackService.approveFeedback(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
