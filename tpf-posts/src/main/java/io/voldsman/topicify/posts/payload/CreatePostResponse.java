package io.voldsman.topicify.posts.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePostResponse {

    private String postId;
}
