package io.voldsman.topicify.users.payload;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String username;
    private String password;
    private boolean isBlocked;
    private boolean isDeleted;
}
