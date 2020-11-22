package me.namila.rxspringboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "me.namila.rxspringboot.repository")
@EnableMongoAuditing
public class MongoReactive {
}
