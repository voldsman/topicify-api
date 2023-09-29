package io.voldsman.topicify.topics.repository;

import io.voldsman.topicify.topics.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
}
