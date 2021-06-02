package org.yx.mongotest.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 */
@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class JwtProperties {

    /**
     * FIXME 密钥后续不能放在源码中
     */
    private String privateKey;
}
