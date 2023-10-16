package io.voldsman.topicify.posts.service;

import io.voldsman.topicify.posts.payload.CreatePostRequest;
import io.voldsman.topicify.posts.payload.CreatePostResponse;

public interface PostService {

    CreatePostResponse create(final String userId, final CreatePostRequest createPostRequest);
}
