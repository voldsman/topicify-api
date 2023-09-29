package io.voldsman.topicify.common.event.payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProfile(UUID userId, String username, LocalDateTime time) {
}
