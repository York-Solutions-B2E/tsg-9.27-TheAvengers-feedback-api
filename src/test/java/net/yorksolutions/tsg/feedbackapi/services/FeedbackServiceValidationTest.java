package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FeedbackServiceValidationTest {

    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        feedbackService = new FeedbackService();
    }

    @Test
    void validRequest_passesValidation() {
        FeedbackRequest req = new FeedbackRequest("m-123", "Dr. Smith", 5, "Excellent visit.");
        assertThatCode(() -> feedbackService.submitFeedback(req)).doesNotThrowAnyException();
    }

    @Test
    void memberId_missing_throwsException() {
        FeedbackRequest req = new FeedbackRequest(null, "Dr. Smith", 4, "Fine");
        assertThatThrownBy(() -> feedbackService.submitFeedback(req))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("memberId");
    }

    @Test
    void rating_outOfRange_throwsException() {
        FeedbackRequest req = new FeedbackRequest("m-123", "Dr. Smith", 7, "Too high");
        assertThatThrownBy(() -> feedbackService.submitFeedback(req))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("rating");
    }

    @Test
    void comment_tooLong_throwsException() {
        String longComment = "A".repeat(201);
        FeedbackRequest req = new FeedbackRequest("m-123", "Dr. Smith", 5, longComment);
        assertThatThrownBy(() -> feedbackService.submitFeedback(req))
                .isInstanceOf(ValidationException.class)
                .hasMessageContaining("comment");
    }
}

