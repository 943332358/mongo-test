package org.yx.mongotest.oauth2Server.client.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
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
    @Id
    private String clientId;

    private String clientName;

    private ClientState clientState;

    /**
     * 客户端回调地址
     */
    private String callbackUrl;

    /**
     * 客户端logo文件Id
     */
    private String clientLogo;

    /**
     * 客户端密钥(客户端本地保存。勿用于网络传输与代码中硬编码，可能会被反编译)
     */
    private String clientSecrets;

}
