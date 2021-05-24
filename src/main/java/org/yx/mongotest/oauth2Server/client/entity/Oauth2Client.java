package org.yx.mongotest.oauth2Server.client.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
@Document
public class Oauth2Client {
    /**
     * 客户端id
     */
    private String clientId;

    private String clientName;

    private ClientState clientState;

    /**
     * 客户端回调地址
     */
    private String callbackUrl;

    private String clientLogo;
}
