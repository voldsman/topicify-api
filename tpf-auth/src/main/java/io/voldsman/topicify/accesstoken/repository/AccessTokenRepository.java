package io.voldsman.topicify.accesstoken.repository;

import io.voldsman.topicify.accesstoken.model.AccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {

    Optional<AccessToken> findByAccessToken(final String accessToken);

    List<AccessToken> findByUserId(final UUID userId);
}
