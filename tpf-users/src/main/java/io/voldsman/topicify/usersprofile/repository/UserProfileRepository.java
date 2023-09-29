package io.voldsman.topicify.usersprofile.repository;

import io.voldsman.topicify.usersprofile.module.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByUserId(final UUID userId);
}
