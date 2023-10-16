package io.voldsman.topicify.accesstoken.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccessTokenDto {

    private String userId;

    private LocalDateTime createdAt;
}
