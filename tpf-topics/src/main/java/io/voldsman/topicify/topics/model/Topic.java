package io.voldsman.topicify.topics.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "updatedAt"})

@Document(collection = "topics")
public class Topic {

    @Id
    private String id;

    @Indexed
    private String topicId;

    @Indexed
    private String userId;

    private String name;
    private String description;

    private boolean isPrivate;

    @Indexed
    private boolean isArchived;
    private LocalDateTime archivedAt;

    private boolean isDeleted;
    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    @Indexed(direction = IndexDirection.DESCENDING)
    private LocalDateTime updatedAt;
}
