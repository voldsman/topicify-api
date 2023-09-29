package io.voldsman.topicify.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoRepositories(basePackages = {
        "io.voldsman.topicify.users.repository",
        "io.voldsman.topicify.users.profile.repository"
})
@EnableTransactionManagement
public class MongoConfig {
}
