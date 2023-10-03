package io.voldsman.topicify.posts.repository;

import io.voldsman.topicify.posts.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
