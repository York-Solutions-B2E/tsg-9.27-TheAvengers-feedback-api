package net.yorksolutions.tsg.feedbackapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="feedback")
public class FeedbackEntity {

    /**
     * SECTION: Table-Entity Variables
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 36)
    @NotBlank
    @Size(max = 36)
    private String memberId;

    @Column(nullable = false, length = 80)
    @NotBlank
    @Size(max = 80)
    private String providerName;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    @NotNull
    private Integer rating;

    @Column(length = 200)
    private String comment;

    @Column(nullable = false)
    private OffsetDateTime submittedAt;

    /**
     * SECTION:
     * No-Args Constructor
     */
    public FeedbackEntity() {}

    /**
     * SECTION: All-Args Constructor
     */
    public FeedbackEntity(UUID id, String memberId, String providerName, Integer rating, String comment, OffsetDateTime submittedAt) {
        this.id = id;
        this.memberId = memberId;
        this.providerName = providerName;
        this.rating = rating;
        this.comment = comment;
        this.submittedAt = submittedAt;
    }

    /**
     * SECTION: Getter/Setter Methods
     */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public OffsetDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(OffsetDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    /**
     * SECTION: Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * SECTION: Override for toString()
     */
    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", submittedAt=" + submittedAt +
                '}';
    }
}