package io.voldsman.topicify.topics.repository;

import io.voldsman.topicify.topics.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TopicRepository extends MongoRepository<Topic, String> {

    long countByUserIdAndIsArchivedFalseAndIsDeletedFalse(final String userId);

    Optional<Topic> findByTopicIdAndIsDeletedIsFalse(final String topicId);
}
