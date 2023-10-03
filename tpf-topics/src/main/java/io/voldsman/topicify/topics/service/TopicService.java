package io.voldsman.topicify.topics.service;

import io.voldsman.topicify.topics.payload.TopicDTO;

import java.util.UUID;

public interface TopicService {

    TopicDTO getByTopicId(final UUID topicId, final UUID userId);
}
