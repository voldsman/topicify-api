package io.voldsman.topicify.topics.payload;

import lombok.Data;

@Data
public class CreateTopicRequest {

    private String name;

    private String description;
}
