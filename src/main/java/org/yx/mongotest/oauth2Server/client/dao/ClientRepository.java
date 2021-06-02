package org.yx.mongotest.oauth2Server.client.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

import java.util.Optional;

/**
 * @author yangxin
 */
public interface ClientRepository extends MongoRepository<Oauth2Client, String> {

    Optional<Oauth2Client> findClientSecretsByClientId(String clientId);

    Optional<Oauth2Client> findCallbackUrlByClientId(String clientId);
}
