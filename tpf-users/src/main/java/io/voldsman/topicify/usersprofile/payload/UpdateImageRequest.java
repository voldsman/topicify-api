package io.voldsman.topicify.usersprofile.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateImageRequest {

    private ImageType type;

    @NotNull(message = "image must not be null")
    @Pattern(regexp = Validations.IMAGE_VALIDATION_REGEX, message = "must match uuid format followed by image extension")
    private String image;

    public enum ImageType {
        AVATAR, COVER
    }
}
