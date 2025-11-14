package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.ErrorResponse;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for Validation Exception (Service Layer)")
public class ValidationExceptionTests {

    /*
     * SECTION: Service / Repository Mocking
     */

    // DESC: Initialize 'stubs' for external/complex dependencies
//    @Mock
//    private BindingResult stubbedBindingResult;

    // DESC: Initialize subject-file 'mock'
    @InjectMocks
    private ValidationException mockedValidationException;

    // DESC: Pull in all 'stubs' before each test
    // NOTE: This provides a 'clean slate' for each test
    // ... in lieu of re-using a 'stub' across the board
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    /**
     * The following test basically ensures that, when a BindingResult
     * is given a FieldError, it will properly map to the ErrorResponse
     * DTO that was created for the Client to receive.
     */
    @Test
    @DisplayName("Ensure that BindingResult returns no errors")
    public void convertBindingResultToErrorResponse_returnErrorResponse_givenFieldError() {
        /* SECTION: Given Input Arrange[ment]... */

        HashMap<String, String> dummyErrorFieldsAndMessages = new HashMap<>();
        dummyErrorFieldsAndMessages.put(
                "memberId",
                "memberId MUST be between 3 and 36 characters AND use alphanumeric characters (e.g., m-123)"
        );

        // NOTE: The `objectName` is the name of the target-object, which
        // ... is a `FeedbackRequest` in this example -- for testing purposes
        // ... the name itself is NOT relevant... just aim to be consistent
        FieldError dummyFieldError = new FieldError(
                "feedbackRequest", "memberId",
                "memberId MUST be between 3 and 36 characters AND use alphanumeric characters (e.g., m-123)"
        );

        // NOTE: The `objectName` is the name of the target-object, which
        // ... is a `FeedbackRequest` in this example -- for testing purposes
        // ... the name itself is NOT relevant... just aim to be consistent
        BindingResult dummyBindingResult = new BeanPropertyBindingResult(
                new FeedbackRequest(), "feedbackRequest"
        );

        dummyBindingResult.addError(dummyFieldError);

        ErrorResponse expectedErrorResponse = new ErrorResponse(
                "Errors", "Validation Failed", dummyErrorFieldsAndMessages
        );

        /* SECTION: When Act[ed] Upon... */

        ErrorResponse actualErrorResponse = mockedValidationException
                .convertBindingResultToErrorResponse(dummyBindingResult);

        /* SECTION: Then Assert Output Is... */

        // NOTE: This is testing against all properties within the subject
        // ... Object because there is no unique ID-field to compare against
        Assertions.assertAll("ErrorResponse Properties",
                () -> Assertions.assertEquals(expectedErrorResponse.getStatus(), actualErrorResponse.getStatus()),
                () -> Assertions.assertEquals(expectedErrorResponse.getMessage(), actualErrorResponse.getMessage()),
                () -> Assertions.assertEquals(expectedErrorResponse.getErrors(), actualErrorResponse.getErrors())
        );
    }

    // TODO: Unit-Test for `convertBindingResultToErrorResponse`

    // TODO: Unit-Test for `unreadableMessageHandler`

    // TODO: Unit-Test for `ratingOmittedHandler`

    // TODO: Unit-Test for `dataFieldsOmittedHandler`

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