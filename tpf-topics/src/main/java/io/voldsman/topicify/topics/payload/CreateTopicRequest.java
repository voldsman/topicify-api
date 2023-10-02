package io.voldsman.topicify.topics.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTopicRequest {

    @NotNull(message = "name must not be null")
    @Size(min = Validations.MIN_TOPIC_NAME_LENGTH, max = Validations.MAX_TOPIC_NAME_LENGTH)
    private String name;

    @Size(max = Validations.MAX_TOPIC_DESCRIPTION_LENGTH)
    private String description;
}
