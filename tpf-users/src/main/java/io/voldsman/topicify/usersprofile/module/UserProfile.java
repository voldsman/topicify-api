package io.voldsman.topicify.usersprofile.module;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "userId"})

@Document(collection = "users_profile")
public class UserProfile {

    @Id
    private String id;

    @Indexed(unique = true)
    private UUID userId;

    private String avatarImage;

    private String coverImage;

    private String bio;

    private List<UserProfileLink> links;

    private LocalDateTime updatedAt;
}
