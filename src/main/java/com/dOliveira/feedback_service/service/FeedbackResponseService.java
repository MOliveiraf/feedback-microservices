package com.dOliveira.feedback_service.service;

import com.dOliveira.feedback_service.dto.FeedbackResponseDTO;
import com.dOliveira.feedback_service.entity.FeedbackResponse;
import com.dOliveira.feedback_service.repository.FeedbackResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackResponseService {

    private final FeedbackResponseRepository feedbackResponseRepository;

    public List<FeedbackResponseDTO> getAllResponses() {
        return feedbackResponseRepository.findAll()
                .stream()
                .map(response -> new FeedbackResponseDTO(
                        response.getId(),
                        response.getFeedbackId(),
                        response.getUserId(),
                        response.getMessage(),
                        response.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public Optional<FeedbackResponseDTO> getResponseById(Long id) {
        return feedbackResponseRepository.findById(id)
                .map(response -> new FeedbackResponseDTO(
                        response.getId(),
                        response.getFeedbackId(),
                        response.getUserId(),
                        response.getMessage(),
                        response.getCreatedAt()
                ));
    }

    public List<FeedbackResponseDTO> getResponsesByFeedbackId(Long feedbackId) {
        return feedbackResponseRepository.findByFeedbackId(feedbackId)
                .stream()
                .map(response -> new FeedbackResponseDTO(
                        response.getId(),
                        response.getFeedbackId(),
                        response.getUserId(),
                        response.getMessage(),
                        response.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public FeedbackResponseDTO createResponse(FeedbackResponse response) {
        FeedbackResponse savedResponse = feedbackResponseRepository.save(response);
        return new FeedbackResponseDTO(
                savedResponse.getId(),
                savedResponse.getFeedbackId(),
                savedResponse.getUserId(),
                savedResponse.getMessage(),
                savedResponse.getCreatedAt()
        );
    }

    public void deleteResponse(Long id) {
        feedbackResponseRepository.deleteById(id);
    }
}
