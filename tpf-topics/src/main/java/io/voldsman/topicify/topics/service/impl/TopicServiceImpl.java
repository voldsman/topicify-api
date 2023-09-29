package io.voldsman.topicify.topics.service.impl;

import io.voldsman.topicify.topics.repository.TopicRepository;
import io.voldsman.topicify.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
}
