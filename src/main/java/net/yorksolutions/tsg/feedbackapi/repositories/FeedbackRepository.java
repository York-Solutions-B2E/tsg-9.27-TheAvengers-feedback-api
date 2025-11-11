package net.yorksolutions.tsg.feedbackapi.repositories;

import net.yorksolutions.tsg.feedbackapi.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, UUID> {

}
