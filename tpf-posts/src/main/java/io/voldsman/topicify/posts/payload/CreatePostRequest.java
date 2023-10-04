package io.voldsman.topicify.posts.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreatePostRequest {

    @NotNull(message = "topicId must not be null")
    @Pattern(regexp = Validations.UUID_VALIDATION_REGEX, message = "Not valid UUID format")
    private String topicId;

    @Size(max = Validations.MAX_POST_BODY_LENGTH)
    private String body;

    @NotNull
    private List<String> images;
}
