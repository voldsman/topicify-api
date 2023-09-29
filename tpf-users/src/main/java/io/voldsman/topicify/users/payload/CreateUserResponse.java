package io.voldsman.topicify.users.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserResponse {

    private String userId;

    public CreateUserResponse(final UUID userId) {
        this.userId = userId.toString();
    }
}
