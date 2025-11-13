package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import net.yorksolutions.tsg.feedbackapi.entities.FeedbackEntity;
import net.yorksolutions.tsg.feedbackapi.repositories.FeedbackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test for Feedback (Service Layer)")
public class FeedbackServiceTests {

    /*
     * SECTION: Service / Repository Mocking
     */

    // DESC: Initialize 'stubs' for external/complex dependencies
    @Mock
    private FeedbackRepository stubbedFeedbackRepository;

    // DESC: Initialize subject-file 'mock'
    @InjectMocks
    private FeedbackService mockedFeedbackService;

    // DESC: Pull in all 'stubs' before each test
    // NOTE: This provides a 'clean slate' for each test
    // ... in lieu of re-using a 'stub' across the board
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Ensure proper DTO-to-Entity conversion")
    public void mapRequestToEntity_returnFeedbackEntity_givenValidRequest() {
        /* SECTION: Given Input Arrange[ment]... */
        FeedbackRequest dummyFeedbackRequest = new FeedbackRequest(
                "m-123", "Dr. Smith",
                "5", "Great experience"
        );

        OffsetDateTime deterministicSubmittedAtDate = OffsetDateTime.now();

        FeedbackEntity expectedFeedbackEntity = new FeedbackEntity(
                dummyFeedbackRequest.getMemberId(), dummyFeedbackRequest.getProviderName(),
                Integer.valueOf(dummyFeedbackRequest.getRating()), dummyFeedbackRequest.getComment()
        );

        expectedFeedbackEntity
                .setSubmittedAt(deterministicSubmittedAtDate);

        /* SECTION: When Act[ed] Upon... */
        FeedbackEntity actualReturnedValue = mockedFeedbackService
                .mapRequestToEntity(dummyFeedbackRequest, deterministicSubmittedAtDate);

        /* SECTION: Then Assert Output Is... */
        Assertions.assertAll("FeedbackEntity Properties",
                () -> Assertions.assertSame(expectedFeedbackEntity.getMemberId(), actualReturnedValue.getMemberId()),
                () -> Assertions.assertSame(expectedFeedbackEntity.getProviderName(), actualReturnedValue.getProviderName()),
                () -> Assertions.assertSame(expectedFeedbackEntity.getRating(), actualReturnedValue.getRating()),
                () -> Assertions.assertSame(expectedFeedbackEntity.getComment(), actualReturnedValue.getComment()),
                () -> Assertions.assertSame(expectedFeedbackEntity.getSubmittedAt(), actualReturnedValue.getSubmittedAt())
        );
    }



//    @Test
//    @DisplayName("...")
//    public void funcName_returnExpectation_expectedOutput() {
//        /* SECTION: Given Input Arrange[ment]... */
//
//        /* SECTION: When Act[ed] Upon... */
//
//        /* SECTION: Then Assert Output Is... */
//    }



}




