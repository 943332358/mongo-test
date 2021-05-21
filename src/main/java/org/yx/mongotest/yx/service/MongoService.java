package org.yx.mongotest.yx.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.yx.mongotest.yx.dao.MongoDao;
import org.yx.mongotest.yx.entity.User;

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

    public int insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User user1 = mongoDao.insert(user);
        return 0;
    }
}
