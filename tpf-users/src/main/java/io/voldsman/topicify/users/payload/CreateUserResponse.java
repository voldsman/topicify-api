package io.voldsman.topicify.users.payload;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;
    private String email;
    private String password;
    private String passwordConfirmation;
    private boolean isTermsAccepted;
}
