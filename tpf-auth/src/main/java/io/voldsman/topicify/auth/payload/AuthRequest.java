package io.voldsman.topicify.auth.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {

    @NotNull(message = "username must not be null")
    @Size(min = Validations.MIN_USERNAME_LENGTH, max = Validations.MAX_USERNAME_LENGTH)
    @Pattern(regexp = Validations.USERNAME_PATTERN, message = "Invalid username format. It should contain at least one alphabetical character, and underscores (_) or dots (.) are allowed but not consecutively.")
    private String username;

    @NotNull(message = "password must not be null")
    @Size(min = Validations.MIN_PASSWORD_LENGTH, max = Validations.MAX_PASSWORD_LENGTH)
    private String password;

    @NotNull(message = "rememberMe must not be null")
    private Boolean rememberMe;
}
