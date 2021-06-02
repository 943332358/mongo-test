package org.yx.mongotest.oauth2Server.authorization.service;

import com.google.common.io.ByteStreams;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yx.mongotest.gridFs.GridFsService;
import org.yx.mongotest.oauth2Server.authorization.dto.AuthorizeDto;
import org.yx.mongotest.oauth2Server.authorization.dto.UserAuthorizationDto;
import org.yx.mongotest.oauth2Server.client.dao.ClientRepository;
import org.yx.mongotest.oauth2Server.client.dto.Oauth2ClientDto;
import org.yx.mongotest.oauth2Server.client.service.ClientService;
import org.yx.mongotest.security.jwt.JwtProperties;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author yangxin
 */
@Component
@Slf4j
public class AuthorizationService {

    @Resource
    private ClientRepository repository;
    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFsService gridFsService;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ClientService clientService;

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


    /**
     * 生成 code 并重定向到客户端回调地址
     */
    public void redirectClient(UserAuthorizationDto authorization) {
        // FIXME 生成code(目前使用jwt生成 code，不太确定code的具体应用场景(code 10分钟过期，body带上用户已经授权的权限信息))
        String jwtCode = Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getPrivateKey())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant()))
                .claim("authorization", authorization)
                .compact();

        // 获取客户端重定向地址
        String url = clientService.findCallbackUrlById(authorization.getClientId()) + "?code=" + jwtCode;

        log.info("CallbackUrl {}", url);
        restTemplate.getForObject(url, String.class);


    }
}
