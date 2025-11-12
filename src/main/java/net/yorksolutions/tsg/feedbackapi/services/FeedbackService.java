package net.yorksolutions.tsg.feedbackapi.services;

import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackResponse;
import net.yorksolutions.tsg.feedbackapi.entities.FeedbackEntity;
import net.yorksolutions.tsg.feedbackapi.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

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
    public FeedbackEntity createNewFeedbackEntry(
            FeedbackRequest clientInput
    ){
        // DESC: Map `FeedbackRequest` into `FeedbackEntity`
        FeedbackEntity convertedInput = new FeedbackEntity(
                clientInput.getMemberId(), clientInput.getProviderName(),
                Integer.valueOf(clientInput.getRating()), clientInput.getComment()
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

    public ArrayList<FeedbackResponse> mapEntitiesToResponse(
            ArrayList<FeedbackEntity> returnedDatabaseEntities
    ) {
        ArrayList<FeedbackResponse> convertedOutput = new ArrayList<>();

        // DESC: Short-circuit if nothing in list (i.e., return empty list)
        if (returnedDatabaseEntities.isEmpty()) {
            return convertedOutput;
        }

        // DESC: Pass each to the singular method above
        for (FeedbackEntity returnedDatabaseEntity : returnedDatabaseEntities) {
            convertedOutput.add(mapEntityToResponse(returnedDatabaseEntity));
        }

        return convertedOutput;
    }

    public FeedbackEntity getFeedbackById(UUID id) {
        Optional<FeedbackEntity> feedbackThatMayExist = feedbackRepository.findById(id);
        return feedbackThatMayExist.orElse(null);
    }

    public ArrayList<FeedbackEntity> getAllFeedbackByMemberId(
            String memberId
    ) {
        ArrayList<FeedbackEntity> returnedDatabaseEntities = feedbackRepository
                .findByMemberId(memberId);

        return returnedDatabaseEntities;
    }
}
