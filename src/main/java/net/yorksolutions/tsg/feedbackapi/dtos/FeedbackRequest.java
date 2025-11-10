package net.yorksolutions.tsg.feedbackapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {
    private String memberId;
    private String providerName;
    private Integer rating;
    private String comment;
}

