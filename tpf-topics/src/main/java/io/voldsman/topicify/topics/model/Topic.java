package io.voldsman.topicify.topics.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID topicId;

    @Indexed
    private UUID userId;

    private String title;

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
