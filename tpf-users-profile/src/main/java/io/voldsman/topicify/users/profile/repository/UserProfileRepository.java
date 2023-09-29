package io.voldsman.topicify.users.profile.repository;

import io.voldsman.topicify.users.profile.module.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
}
