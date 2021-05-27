package org.yx.mongotest.oauth2Server.client.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.yx.mongotest.oauth2Server.client.dao.ClientRepository;
import org.yx.mongotest.oauth2Server.client.entity.ClientState;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author yangxin
 */
@Component
@Slf4j
public class ClientService {

    @Resource
    private ClientRepository repository;

    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * TODO 校验用户输入参数、回调地址合法性
     */
    public Oauth2Client insert(Oauth2Client oauth2Client, MultipartFile clientLogoFile) {
        // 设置客户端密钥
        oauth2Client.setClientSecrets(getClientSecrets());
        // 设置状态
        oauth2Client.setClientState(ClientState.ENABLE);
        // 上传设置客户端logo
        oauth2Client.setClientLogo(Optional.ofNullable(clientLogoFile)
                .map(m -> {
                    try {
                        ObjectId objectId = gridFsTemplate.store(m.getInputStream(), Objects.requireNonNull(m.getOriginalFilename()));
                        log.info("file objectId {}", objectId);

                        return objectId.toHexString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })
                .orElseGet(() -> Optional.ofNullable(gridFsTemplate.findOne(Query.query(Criteria.where("metadata.default").is(true))))
                        .map(m -> m.getObjectId().toHexString())
                        .orElseThrow(RuntimeException::new)));


        return repository.insert(oauth2Client);
    }

    /**
     * FIXME 客户端密钥生成逻辑暂不确定
     */
    public String getClientSecrets() {
        return UUID.randomUUID().toString();
    }


}
