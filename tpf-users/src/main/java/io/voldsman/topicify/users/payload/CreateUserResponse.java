package io.voldsman.topicify.users.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserResponse {

    private String userId;
}
