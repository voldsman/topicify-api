package io.voldsman.topicify.topics.service;

import io.voldsman.topicify.topics.payload.CreateTopicRequest;
import io.voldsman.topicify.topics.payload.CreateTopicResponse;

import java.util.UUID;

public interface CreateTopicService {

    CreateTopicResponse create(final UUID userId, final CreateTopicRequest createTopicRequest);
}
