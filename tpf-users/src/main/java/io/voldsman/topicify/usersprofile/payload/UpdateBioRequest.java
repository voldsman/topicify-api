package io.voldsman.topicify.usersprofile.payload;

import io.voldsman.topicify.common.constants.Validations;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBioRequest {

    @NotNull
    @Size(max = Validations.MAX_PROFILE_BIO_SYMBOLS_LENGTH)
    private String bio;
}
