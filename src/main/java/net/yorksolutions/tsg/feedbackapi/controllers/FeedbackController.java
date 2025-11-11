package net.yorksolutions.tsg.feedbackapi.controllers;

public class FeedbackController {

    // TODO: POST /feedback
        // NOTE: Input should be validated `(@Valid @RequestBody DTOtype dtoType, BindingResult result)
        // TODO: Map to `FeedbackRequest` (Service-layer to handle getting id from DB / generating submittedAt)

        // NOTE: To allow a versatile return, use the wild-card `public ResponseEntity<?>`
        // NOTE: It is *best-practice* to return a DTO NOT the Entity Object
        // TODO: If Binding Result has errors (no 'else' statement necessary)
            // TODO: Jump into Service Layer to...
                // TODO: Feed Binding Result into `ErrorResponse` DTO (incl. Validation) [Conversion]
            // TODO: Return `ErrorResponse` DTO and 400

        // TODO: Steps for "Happy-Path" (no 'else' statement necessary)...
            // TODO: Jump into `FeedbackService`
                // TODO: Feed `FeedbackRequest` into `FeedbackEntity`
                // TODO: Created submitted at date
                // TODO: Commit to Database
                // TODO: Retrieve ID from new Database entry
                // TODO: Convert to `FeedbackResponse`
            // TODO: Return the `FeedbackResponse` (incl. `id` and `submittedAt`) and 201 OR ...
            // NOTE: Need to look into Kafka to determine how to properly handle the following step
            // TODO: Jump into `FeedbackEventPublisher` / Publish directly with `FeedbackEventPublisher`


    // TODO: GET /feedback/{id}
        // TODO: SUCCESSFUL -- Return 200 with `FeedbackEntity`
        // TODO: FAILED -- Return 404


    // TODO: GET /feedback?memberId=<id>
        // TODO: SUCCESSFUL / FAILED -- Return 200 with List of `FeedbackEntity` (may be empty)


    // TODO: GET /health
        // TODO: Return 200 with a "simple health check"
}
