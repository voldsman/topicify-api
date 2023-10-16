package io.voldsman.topicify.users.repository;

import io.voldsman.topicify.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUsernameIgnoreCase(final String username);

    boolean existsByEmailIgnoreCase(final String email);

    Optional<User> findByUsernameIgnoreCase(final String username);

    Optional<User> findByUserIdAndIsDeletedFalseAndIsBlockedFalse(final String userId);
}
