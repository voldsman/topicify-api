package io.voldsman.topicify.topics.repository;

import io.voldsman.topicify.topics.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends MongoRepository<Topic, String> {

    long countByUserIdAndIsArchivedFalseAndIsDeletedFalse(final UUID userId);

    Optional<Topic> findByTopicIdAndIsDeletedIsFalse(final UUID topicId);
}
