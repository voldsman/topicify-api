package io.voldsman.topicify.topics.service.impl;

import io.voldsman.topicify.common.exception.ForbiddenException;
import io.voldsman.topicify.common.exception.NotFoundException;
import io.voldsman.topicify.topics.model.Topic;
import io.voldsman.topicify.topics.payload.TopicDto;
import io.voldsman.topicify.topics.repository.TopicRepository;
import io.voldsman.topicify.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public TopicDto getByTopicId(final String topicId, final String userId) {
        Optional<Topic> topicOptional = topicRepository.findByTopicIdAndIsDeletedIsFalse(topicId);
        if (topicOptional.isEmpty()) {
            throw new NotFoundException("Topic not found by provided id");
        }

        Topic topic = topicOptional.get();
        if (!topic.getUserId().equals(userId)) {
            throw new ForbiddenException("Not allowed. User isn't resource owner");
        }

        TopicDto topicDTO = new TopicDto();
        topicDTO.setTopicId(topic.getTopicId());
        topicDTO.setName(topic.getName());
        topicDTO.setDescription(topic.getDescription());
        topicDTO.setArchived(topic.isArchived());
        topicDTO.setArchivedAt(topic.getArchivedAt());
        topicDTO.setUpdatedAt(topic.getUpdatedAt());
        return topicDTO;
    }
}
