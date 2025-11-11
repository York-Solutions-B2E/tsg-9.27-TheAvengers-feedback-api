package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.ErrorResponse;
import net.yorksolutions.tsg.feedbackapi.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

/**
 * Handle all necessary business logic up to and including
 * the Kafka publishing.
 */
@Service
public class FeedbackService {

    /*
     * SECTION: Constructor-based D.I. (w/o @Autowired)
     */

    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /*
     * SECTION: FeedbackService Methods
     */

    public ErrorResponse convertBindingResultToErrorResponse(
            BindingResult invalidUserInputError
    ) {
        // DESC: Map for 'field' - 'message' (from BindingResult)
        HashMap<String, String> errorFieldsAndMessages = new HashMap<>();

        // DESC: Loop through FieldErrors and update Map
        for (FieldError fieldError : invalidUserInputError.getFieldErrors()) {
            errorFieldsAndMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        // DESC: Create ErrorResponse (with HashMap data)
        ErrorResponse userValidErrResponse = new ErrorResponse(
                "Errors", "Validation Failed",  errorFieldsAndMessages
        );

        return userValidErrResponse;
    }




//    public FeedbackResponse submitFeedback(FeedbackRequest request) {
//        validate(request);
//        // TODO: Persist via repository and publish to Kafka (later)
//        return null;
//    }
//
//    void validate(FeedbackRequest request) {
//        // Validation rules
//        if (request.getMemberId() == null || request.getMemberId().isBlank()) {
//            throw new ValidationException("memberId", "Required and cannot be empty");
//        }
//        if (request.getMemberId().length() > 36) {
//            throw new ValidationException("memberId", "Must be ≤ 36 characters");
//        }
//
//        if (request.getProviderName() == null || request.getProviderName().isBlank()) {
//            throw new ValidationException("providerName", "Required and cannot be empty");
//        }
//        if (request.getProviderName().length() > 80) {
//            throw new ValidationException("providerName", "Must be ≤ 80 characters");
//        }
//
//        if (request.getRating() == null || request.getRating() < 1 || request.getRating() > 5) {
//            throw new ValidationException("rating", "Must be between 1 and 5");
//        }
//
//        if (request.getComment() != null && request.getComment().length() > 200) {
//            throw new ValidationException("comment", "Must be ≤ 200 characters");
//        }
//    }
}
