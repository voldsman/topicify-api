package io.voldsman.topicify.posts.controller;

import io.voldsman.topicify.common.constants.Defaults;
import io.voldsman.topicify.common.constants.Routes;
import io.voldsman.topicify.common.payload.ApiResponse;
import io.voldsman.topicify.posts.payload.CreatePostRequest;
import io.voldsman.topicify.posts.payload.CreatePostResponse;
import io.voldsman.topicify.posts.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(Routes.CREATE_POST_ROUTE)
    public ResponseEntity<ApiResponse<CreatePostResponse>> create(@RequestAttribute(Defaults.REQUEST_ATTR_USERID_PARAM) UUID userId,
                                                                  @Valid @RequestBody CreatePostRequest createPostRequest) {
        CreatePostResponse createPostResponse = postService.create(userId, createPostRequest);

        var apiResponse = new ApiResponse<>("Post successfully created", true, createPostResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
