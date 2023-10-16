package io.voldsman.topicify.refreshtoken.repository;

import io.voldsman.topicify.refreshtoken.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findByRefreshToken(final String refreshTokenString);

    List<RefreshToken> findByUserId(final String userId);
}
