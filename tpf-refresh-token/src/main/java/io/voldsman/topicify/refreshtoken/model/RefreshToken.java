package io.voldsman.topicify.refreshtoken.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "refreshToken"})

@Document(collection = "refresh_tokens")
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private UUID userId;

    @Indexed(unique = true)
    private String refreshToken;

    private String deviceFingerprint;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}
