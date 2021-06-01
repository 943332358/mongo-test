package org.yx.mongotest.oauth2Server.authorization.service;

import com.google.common.io.ByteStreams;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.yx.mongotest.gridFs.GridFsService;
import org.yx.mongotest.oauth2Server.authorization.dto.AuthorizeDto;
import org.yx.mongotest.oauth2Server.client.dao.ClientRepository;
import org.yx.mongotest.oauth2Server.client.dto.Oauth2ClientDto;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author yangxin
 */
@Component
public class AuthorizationService {

    @Resource
    private ClientRepository repository;

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFsService gridFsService;

    /**
     * 校验客户端是否合法
     */
    public Oauth2ClientDto clientCheck(AuthorizeDto authorize) {
        final Function<GridFSFile, String> function = f -> {
            try {
                return Base64.getEncoder().encodeToString(ByteStreams.toByteArray(gridFsTemplate.getResource(f).getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("file to base64 failed");
            }
        };

        return repository.findById(authorize.getClientId())
                .map(m -> {
                    GridFSFile fsFile = Optional.ofNullable(m.getClientLogo())
                            .map(logo -> gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(logo))))
                            .orElseGet(() -> gridFsService.getDefaultLogo());

                    Oauth2ClientDto clientDto = new Oauth2ClientDto().setClientLogoBase64(function.apply(fsFile));
                    BeanUtils.copyProperties(m, clientDto);
                    return clientDto;
                })
                .orElseThrow(() -> new RuntimeException("client not found." + authorize.getClientId()));
    }


}
