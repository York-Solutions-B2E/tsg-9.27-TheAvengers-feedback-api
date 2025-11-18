package net.yorksolutions.tsg.feedbackapi.messaging;

import net.yorksolutions.tsg.feedbackapi.dtos.Feedback;
import net.yorksolutions.tsg.feedbackapi.dtos.FeedbackResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeedbackEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public FeedbackEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String SCHEMA_VERSION = "1.0.0";
    @Value("${topic.name}")
    private String topicName;

    public void sendFeedbackEvent(FeedbackResponse feedbackResponse) {
        Feedback feedbackEvent = Feedback.newBuilder()
                .setId(feedbackResponse.getId().toString())
                .setMemberId(feedbackResponse.getMemberId())
                .setProviderName(feedbackResponse.getProviderName())
                .setRating(feedbackResponse.getRating())
                .setComment(feedbackResponse.getComment())
                .setSubmittedAt(feedbackResponse.getSubmittedAt().toInstant().toEpochMilli())
                .setSchemaVersion(SCHEMA_VERSION)
                .build();



        kafkaTemplate.send(topicName, feedbackEvent.getId().toString(), feedbackEvent);
        System.out.println("Sent feedback event: " + feedbackEvent);
    }
}
