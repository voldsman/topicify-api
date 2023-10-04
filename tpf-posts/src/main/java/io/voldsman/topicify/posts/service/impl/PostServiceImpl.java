package io.voldsman.topicify.posts.service.impl;

import io.voldsman.topicify.posts.model.Post;
import io.voldsman.topicify.posts.payload.CreatePostRequest;
import io.voldsman.topicify.posts.payload.CreatePostResponse;
import io.voldsman.topicify.posts.repository.PostRepository;
import io.voldsman.topicify.posts.service.PostService;
import io.voldsman.topicify.topics.payload.TopicDTO;
import io.voldsman.topicify.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final TopicService topicService;

    @Override
    public CreatePostResponse create(final UUID userId, final CreatePostRequest createPostRequest) {

        UUID topicId = UUID.fromString(createPostRequest.getTopicId());
        TopicDTO topicDTO = topicService.getByTopicId(topicId, userId);

        LocalDateTime now = LocalDateTime.now();

        Post post = new Post();
        post.setPostId(UUID.randomUUID());
        post.setTopicId(topicDTO.getTopicId());
        post.setBody(createPostRequest.getBody());
        if (!createPostRequest.getImages().isEmpty()) {
            post.setImages(createPostRequest.getImages());
        }
        post.setCreatedAt(now);
        post.setUpdatedAt(now);
        post.setDeleted(false);
        post.setDeletedAt(null);
        final Post persistedPost = postRepository.save(post);
        // TODO: create newsfeed record
        // TODO: add images
        return new CreatePostResponse(persistedPost.getPostId());
    }
}
