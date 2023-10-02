package io.voldsman.topicify.usersprofile.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateLinksRequest {

    @Valid
    @Size(max = Validations.MAX_PROFILE_LINKS)
    private List<Link> links;

    @Data
    public static class Link {
        @NotNull(message = "name must not be null")
        @Size(max = Validations.MAX_PROFILE_LINK_NAME_LENGTH)
        private String name;

        @NotNull(message = "url must not be null")
        @Size(max = Validations.MAX_PROFILE_LINK_URL_LENGTH)
        @Pattern(regexp = Validations.PROFILE_URL_REGEX, message = "invalid url format")
        private String url;
    }
}
