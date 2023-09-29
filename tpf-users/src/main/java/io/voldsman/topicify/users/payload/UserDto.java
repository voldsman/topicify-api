package io.voldsman.topicify.users.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID userId;
    private String username;
    private String password;
    private boolean isBlocked;
    private boolean isDeleted;
}
