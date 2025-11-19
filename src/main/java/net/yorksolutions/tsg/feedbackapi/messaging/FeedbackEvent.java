package net.yorksolutions.tsg.feedbackapi.messaging;

import java.time.OffsetDateTime;
import java.util.UUID;

public record FeedbackEvent(
        UUID id,
        String memberId,
        String providerName,
        int rating,
        String comment,
        OffsetDateTime submittedAt,
        String schemaVersion
) {}
