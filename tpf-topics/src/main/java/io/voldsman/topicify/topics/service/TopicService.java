package io.voldsman.topicify.topics.service;

import io.voldsman.topicify.topics.payload.TopicDto;

public interface TopicService {

    TopicDto getByTopicId(final String topicId, final String userId);
}
