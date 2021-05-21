package org.yx.mongotest.yx.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yx.mongotest.yx.entity.User;

import java.util.Optional;

/**
 * @author yangxin
 */
public interface MongoDao extends MongoRepository<User, String> {


    Optional<User> findByName(String name);
}
