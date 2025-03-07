package com.dOliveira.feedback_service.service;


import com.dOliveira.feedback_service.dto.FeedbackDTO;
import com.dOliveira.feedback_service.entity.Feedback;
import com.dOliveira.feedback_service.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(feedback -> new FeedbackDTO(
                        feedback.getId(),
                        feedback.getUserId(),
                        feedback.getMessage(),
                        feedback.getStatus(),
                        feedback.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(feedback -> new FeedbackDTO(
                        feedback.getId(),
                        feedback.getUserId(),
                        feedback.getMessage(),
                        feedback.getStatus(),
                        feedback.getCreatedAt()
                ));
    }

    public FeedbackDTO createFeedback(Feedback feedback) {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return new FeedbackDTO(
                savedFeedback.getId(),
                savedFeedback.getUserId(),
                savedFeedback.getMessage(),
                savedFeedback.getStatus(),
                savedFeedback.getCreatedAt()
        );
    }

    public Optional<FeedbackDTO> approveFeedback(Long id) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setStatus("APPROVED");
            Feedback updatedFeedback = feedbackRepository.save(feedback);
            return new FeedbackDTO(
                    updatedFeedback.getId(),
                    updatedFeedback.getUserId(),
                    updatedFeedback.getMessage(),
                    updatedFeedback.getStatus(),
                    updatedFeedback.getCreatedAt()
            );
        });
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
