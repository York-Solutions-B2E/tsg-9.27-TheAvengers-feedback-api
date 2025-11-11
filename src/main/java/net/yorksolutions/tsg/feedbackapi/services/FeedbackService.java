package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.ErrorResponse;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackResponse;
import net.yorksolutions.tsg.feedbackapi.entities.FeedbackEntity;
import net.yorksolutions.tsg.feedbackapi.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.OffsetDateTime;
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

    public FeedbackEntity createNewFeedbackEntry(
            FeedbackRequest clientInput
    ){
        /* FUTURE: Logic to prevent multiple reviews (NOT in spec) */

        // DESC: Map `FeedbackRequest` into `FeedbackEntity`
        FeedbackEntity convertedInput = new FeedbackEntity(
                clientInput.getMemberId(), clientInput.getProviderName(),
                clientInput.getRating(), clientInput.getComment()
        );

        // DESC: Created submitted at date
        convertedInput.setSubmittedAt(OffsetDateTime.now());

        // DESC: Commit to Database
        FeedbackEntity savedEntity = feedbackRepository.save(convertedInput);

        // DESC: Retrieve ID from new Database entry
        convertedInput.setId(savedEntity.getId());

        return convertedInput;
    }

    public FeedbackResponse mapEntityToResponse(
            FeedbackEntity returnedDatabaseEntity
    ) {
        // DESC: Map `FeedbackEntity` into `FeedbackResponse`
        FeedbackResponse convertedOutput = new FeedbackResponse(
                returnedDatabaseEntity.getId(), returnedDatabaseEntity.getMemberId(),
                returnedDatabaseEntity.getProviderName(), returnedDatabaseEntity.getRating(),
                returnedDatabaseEntity.getComment(), returnedDatabaseEntity.getSubmittedAt()
        );

        return convertedOutput;
    }
}
