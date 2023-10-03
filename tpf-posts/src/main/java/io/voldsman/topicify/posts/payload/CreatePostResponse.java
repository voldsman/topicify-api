package io.voldsman.topicify.posts.payload;

import lombok.Data;

import java.util.UUID;

//TODO: add validations
@Data
public class CreatePostResponse {

    private String postId;

    public CreatePostResponse(final UUID postId) {
        this.postId = postId.toString();
    }
}
