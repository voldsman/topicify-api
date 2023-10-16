package io.voldsman.topicify.posts.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "topicId", "updatedAt"})

@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    @Indexed
    private String postId;

    @Indexed
    private String topicId;

    @Indexed
    private String userId;

    private String body;

    private List<String> images;

    private boolean isDeleted;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;

    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime updatedAt;
}
