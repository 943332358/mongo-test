package org.yx.mongotest.authorization.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yx.mongotest.authorization.entity.User;

import java.util.Optional;

/**
 * @author yangxin
 */
public interface MongoDao extends MongoRepository<User, String> {


    Optional<User> findByName(String name);
}
