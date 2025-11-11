package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FeedbackServiceValidationTest {
//
//    private FeedbackService feedbackService;
//    private FeedbackRequest validRequest;
//
//    @BeforeEach
//    void setUp() {
//        feedbackService = new FeedbackService();
//        validRequest = new FeedbackRequest("m-123", "Dr. Smith", 5, "Excellent visit.");
//    }
//
//    @Test
//    void validRequest_passesValidation() {
//        assertThatCode(() -> feedbackService.submitFeedback(validRequest))
//                .doesNotThrowAnyException();
//    }
//
//    @Test
//    void memberId_missing_throwsException() {
//        validRequest.setMemberId(null);
//        assertThatThrownBy(() -> feedbackService.submitFeedback(validRequest))
//                .isInstanceOf(ValidationException.class)
//                .hasMessageContaining("memberId");
//    }
//
//    @Test
//    void rating_outOfRange_throwsException() {
//        validRequest.setRating(7);
//        assertThatThrownBy(() -> feedbackService.submitFeedback(validRequest))
//                .isInstanceOf(ValidationException.class)
//                .hasMessageContaining("rating");
//    }
//
//    @Test
//    void comment_tooLong_throwsException() {
//        validRequest.setComment("A".repeat(201));
//        assertThatThrownBy(() -> feedbackService.submitFeedback(validRequest))
//                .isInstanceOf(ValidationException.class)
//                .hasMessageContaining("comment");
//    }
}




