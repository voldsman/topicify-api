package io.voldsman.topicify.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
        "io.voldsman.topicify.users.repository",
        "io.voldsman.topicify.usersprofile.repository",
        "io.voldsman.topicify.refreshtoken.repository",
        "io.voldsman.topicify.topics.repository",
        "io.voldsman.topicify.posts.repository"
})
public class MongoConfig {

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
