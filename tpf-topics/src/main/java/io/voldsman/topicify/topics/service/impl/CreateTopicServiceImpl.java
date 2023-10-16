package io.voldsman.topicify.topics.service.impl;

import io.voldsman.topicify.common.constants.Validations;
import io.voldsman.topicify.common.exception.BadRequestException;
import io.voldsman.topicify.topics.model.Topic;
import io.voldsman.topicify.topics.payload.CreateTopicRequest;
import io.voldsman.topicify.topics.payload.CreateTopicResponse;
import io.voldsman.topicify.topics.repository.TopicRepository;
import io.voldsman.topicify.topics.service.CreateTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTopicServiceImpl implements CreateTopicService {

    private final TopicRepository topicRepository;

    @Override
    public CreateTopicResponse create(final String userId, CreateTopicRequest createTopicRequest) {
        long activeTopicsCount = topicRepository.countByUserIdAndIsArchivedFalseAndIsDeletedFalse(userId);
        if (activeTopicsCount > Validations.MAX_ACTIVE_TOPICS) {
            throw new BadRequestException("Reached maximum of active topics count");
        }

        LocalDateTime timeNow = LocalDateTime.now();

        Topic topic = new Topic();
        topic.setUserId(userId);
        topic.setTopicId(UUID.randomUUID().toString());
        topic.setName(createTopicRequest.getName());
        topic.setDescription(createTopicRequest.getDescription());
        topic.setPrivate(false);
        topic.setArchived(false);
        topic.setArchivedAt(null);
        topic.setDeleted(false);
        topic.setDeletedAt(null);
        topic.setCreatedAt(timeNow);
        topic.setUpdatedAt(timeNow);
        Topic persistedTopic = topicRepository.save(topic);

        return new CreateTopicResponse(persistedTopic.getTopicId());
    }
}
