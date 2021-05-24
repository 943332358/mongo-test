package org.yx.mongotest.oauth2Server.client.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

/**
 * @author yangxin
 */
public interface ClientRepository extends MongoRepository<Oauth2Client,String> {
}
