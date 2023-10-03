package io.voldsman.topicify.topics.payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TopicDTO {

    private UUID topicId;

    private String name;
    private String description;
    private boolean isArchived;
    private LocalDateTime archivedAt;
    private LocalDateTime updatedAt;
}
