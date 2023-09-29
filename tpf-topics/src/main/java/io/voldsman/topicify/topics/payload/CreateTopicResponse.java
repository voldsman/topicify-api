package io.voldsman.topicify.topics.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTopicResponse {

    private String topicId;

    public CreateTopicResponse(final UUID topicId) {
        this.topicId = topicId.toString();
    }
}
