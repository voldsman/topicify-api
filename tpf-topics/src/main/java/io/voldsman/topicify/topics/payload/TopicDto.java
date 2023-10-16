package io.voldsman.topicify.topics.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicDto {

    private String topicId;
    private String name;
    private String description;
    private boolean isArchived;
    private LocalDateTime archivedAt;
    private LocalDateTime updatedAt;
}
