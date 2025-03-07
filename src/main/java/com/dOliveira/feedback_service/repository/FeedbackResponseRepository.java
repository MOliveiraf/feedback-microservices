package com.dOliveira.feedback_service.repository;

import com.dOliveira.feedback_service.entity.FeedbackResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackResponseRepository extends JpaRepository<FeedbackResponse, Long> {
    List<FeedbackResponse> findByFeedbackId(Long feedbackId);
}
