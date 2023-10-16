package io.voldsman.topicify.users.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "username"})

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Indexed
    private boolean isDeleted;

    private boolean isBlocked;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
