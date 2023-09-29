package io.voldsman.topicify.accesstoken.payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccessTokenDto {

    private UUID userId;

    private LocalDateTime createdAt;
}
