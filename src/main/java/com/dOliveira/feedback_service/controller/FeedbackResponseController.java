package com.dOliveira.feedback_service.controller;

import com.dOliveira.feedback_service.dto.FeedbackResponseDTO;
import com.dOliveira.feedback_service.entity.FeedbackResponse;
import com.dOliveira.feedback_service.service.FeedbackResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class FeedbackResponseController {

    private final FeedbackResponseService feedbackResponseService;

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllResponses() {
        return ResponseEntity.ok(feedbackResponseService.getAllResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> getResponseById(@PathVariable Long id) {
        return feedbackResponseService.getResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/feedback/{feedbackId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getResponsesByFeedbackId(@PathVariable Long feedbackId) {
        return ResponseEntity.ok(feedbackResponseService.getResponsesByFeedbackId(feedbackId));
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> createResponse(@RequestBody FeedbackResponse response) {
        return ResponseEntity.ok(feedbackResponseService.createResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        feedbackResponseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}

