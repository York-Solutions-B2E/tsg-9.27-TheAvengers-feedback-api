package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FeedbackServiceValidationTest {

    private FeedbackService feedbackService;
    private FeedbackRequest validRequest;

    @BeforeEach
    void setUp() {
        feedbackService = new FeedbackService();
        validRequest = new FeedbackRequest("m-123", "Dr. Smith", 5, "Excellent visit.");
    }

    @Test
    void validRequest_passesValidation() {
        assertThatCode(() -> feedbackService.submitFeedback(validRequest))
                .doesNotThrowAnyException();
    }
}



