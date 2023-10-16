package io.voldsman.topicify.topics.controller;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.topics.payload.CreateTopicRequest;
import io.voldsman.topicify.topics.payload.CreateTopicResponse;
import io.voldsman.topicify.topics.service.CreateTopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.CREATE_TOPIC_ROUTE)
@RequiredArgsConstructor
public class CreateTopicController {

    private final CreateTopicService createTopicService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateTopicResponse>> create(
            @RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) String userId,
            @Valid @RequestBody CreateTopicRequest createTopicRequest) {
        CreateTopicResponse createTopicResponse = createTopicService.create(userId, createTopicRequest);

        var apiResponse = new ApiResponse<>("Topic successfully created", true, createTopicResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
