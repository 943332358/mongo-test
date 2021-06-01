package org.yx.mongotest.authorization.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.yx.mongotest.authorization.dao.MongoDao;
import org.yx.mongotest.authorization.entity.User;

import javax.annotation.Resource;

/**
 * @author yangxin
 */
@Component
public class MongoService {

    @Resource
    private MongoDao mongoDao;
    @Resource
    private PasswordEncoder passwordEncoder;

    public User insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return mongoDao.insert(user);
    }
}
