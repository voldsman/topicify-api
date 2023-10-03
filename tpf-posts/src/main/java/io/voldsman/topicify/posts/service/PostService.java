package io.voldsman.topicify.posts.service;

import io.voldsman.topicify.posts.payload.CreatePostRequest;
import io.voldsman.topicify.posts.payload.CreatePostResponse;

import java.util.UUID;

public interface PostService {

    CreatePostResponse create(final UUID userId, final CreatePostRequest createPostRequest);
}
