package org.yx.mongotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.yx.BaseProperties;

/**
 * @author yangxin
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(BaseProperties.class)
public class MongoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoTestApplication.class, args);
    }

}
