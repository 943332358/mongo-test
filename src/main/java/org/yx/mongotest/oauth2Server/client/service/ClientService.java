package org.yx.mongotest.oauth2Server.client.service;

import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.yx.mongotest.oauth2Server.client.dao.ClientRepository;
import org.yx.mongotest.oauth2Server.client.dto.Oauth2ClientDto;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

import javax.annotation.Resource;

/**
 * @author yangxin
 */
@Component
public class ClientService {

    @Resource
    private ClientRepository repository;

    @Resource
    GridFsTemplate gridFsTemplate;

    public Oauth2Client insert(Oauth2ClientDto oauth2ClientDto) {
        // 上传logo

        return repository.insert(oauth2ClientDto);
    }
}
