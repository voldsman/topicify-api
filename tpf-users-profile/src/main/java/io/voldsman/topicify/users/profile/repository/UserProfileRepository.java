package io.voldsman.topicify.users.profile.repository;

import io.voldsman.topicify.users.profile.module.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByUserId(final UUID userId);
}
