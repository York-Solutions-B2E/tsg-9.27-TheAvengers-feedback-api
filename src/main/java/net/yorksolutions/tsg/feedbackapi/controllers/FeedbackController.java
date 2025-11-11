package net.yorksolutions.tsg.feedbackapi.controllers;

import jakarta.validation.Valid;
import net.yorksolutions.tsg.feedbackapi.dtos.ErrorResponse;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackRequest;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackResponse;
import net.yorksolutions.tsg.feedbackapi.services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {

    /*
     * SECTION: Constructor-based D.I. (w/o @Autowired)
     */

    private FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /*
     * SECTION: End-Points
     */

    /**
     * Client shall provide the following JSON payload:
     * <p>
     * EX: { "memberId": "m-123", "providerName": "Dr. Smith",
     * "rating": 4, "comment": "Great experience." }
     * <p>
     * This method will validate input, interact with necessary methods
     * in the `FeedbackService` method and return the appropriate response
     * @param clientInput mapped to FeedbackRequest DTO
     * @param result the required Spring Validation mechanism
     * @return 201 and create resource | 400 and validation errors (PASS | FAIL)
     */
    // NOTE: `ResponseEntity<?>` is using the wild-card to handle returning
    // ... different DTOs based on PASS or FAIL
    // NOTE: It is *best-practice* to return a DTO ... NOT an Entity
    @PostMapping("/feedback")
    public ResponseEntity<?> createNewFeedbackEntry(
            @Valid @RequestBody FeedbackRequest clientInput, BindingResult result
    ) {
        /*
         * SECTION: Handle necessary Validation-error steps
         */
        if (result.hasErrors()) {
            // DESC: Convert Validation Errors to Client-friendly DTO
            ErrorResponse returnedErrRespDto = feedbackService
                    .convertBindingResultToErrorResponse(result);

            // DESC: Return Client-friendly DTO and HTTP Bad Request
            return ResponseEntity
                    .status(400)
                    .body(returnedErrRespDto);
        }

        /*
         * SECTION: Handle necessary Validation-pass steps
         */
        // TODO: Jump into `FeedbackService`
            // TODO: Feed `FeedbackRequest` into `FeedbackEntity`
            // TODO: Created submitted at date
            // TODO: Commit to Database
            // TODO: Retrieve ID from new Database entry
            // TODO: Convert to `FeedbackResponse`
            // TODO: Return the `FeedbackResponse` (incl. `id` and `submittedAt`) and 201 OR ...
        // NOTE: Need to look into Kafka to determine how to properly handle the following step
        // TODO: Jump into `FeedbackEventPublisher` / Publish directly with `FeedbackEventPublisher`





        // FIXME: Pass an instance of FeedbackResponse
        return ResponseEntity
                .status(200)
                .body(new FeedbackResponse());
    }


    // TODO: GET /feedback/{id}
        // TODO: SUCCESSFUL -- Return 200 with `FeedbackEntity`
        // TODO: FAILED -- Return 404


    // TODO: GET /feedback?memberId=<id>
        // TODO: SUCCESSFUL / FAILED -- Return 200 with List of `FeedbackEntity` (may be empty)


    // TODO: GET /health
        // TODO: Return 200 with a "simple health check"
}
