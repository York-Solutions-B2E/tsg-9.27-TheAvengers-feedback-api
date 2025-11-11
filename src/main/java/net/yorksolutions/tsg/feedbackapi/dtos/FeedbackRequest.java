package net.yorksolutions.tsg.feedbackapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

/**
 * A DTO class that handles/validates the received JSON
 * payload from the Client. Note that this will reject any
 * unknown properties that are encountered, due to faulty
 * Client-input (i.e., too many values in the JSON)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackRequest {

    /*
     * SECTION: DTO / POJO Variables
     */
    @NotBlank(message = "memberId CANNOT be blank.")
    @Size(max = 36, message = "memberId MUST be <= 36 characters.")
    private String memberId;

    @NotBlank(message = "providerName CANNOT be blank.")
    @Size(max = 80, message = "providerName MUST be <= 80 characters.")
    private String providerName;

    @Min(value = 1, message = "rating MUST be >= 1.")
    @Max(value = 5, message = "rating MUST be <= 5.")
    @NotNull(message = "rating CANNOT be blank (and MUST be numeric).")
    private Integer rating;

    @Size(max = 200, message = "comment, IF provided, MUST be <= 200 characters")
    @NotNull(message = "comment can be empty, however it CANNOT be null.")
    private String comment;

    /*
     * SECTION: No-Args Constructor
     */
    public FeedbackRequest() {}

    /*
     * SECTION: All-Args Constructor
     */
    public FeedbackRequest(
            String memberId, String providerName,
            Integer rating, String comment
    ) {
        this.memberId = memberId;
        this.providerName = providerName;
        this.rating = rating;
        this.comment = comment;
    }

    /*
     * SECTION: Getter / Setter Methods
     */
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*
     * SECTION: toString Override
     */
    @Override
    public String toString() {
        return "FeedbackRequest{" +
                "memberId='" + memberId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}

