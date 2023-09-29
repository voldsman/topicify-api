package io.voldsman.topicify.accesstoken.model;

import io.voldsman.topicify.common.constants.Defaults;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = {"accessToken", "userId"})
@NoArgsConstructor

@RedisHash(value = "accessToken", timeToLive = Defaults.ACCESS_TOKEN_EXP)
public class AccessToken {

    @Id
    private String id;

    @Indexed
    private String accessToken;

    @Indexed
    private UUID userId;

    private LocalDateTime createdAt;
}
