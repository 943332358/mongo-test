package org.yx.mongotest.oauth2Server.authorization.dto;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @author yangxin
 */
@Data
public class AccessTokenDto {
    @NotNull
    public String clientId;

    @NotNull
    public String clientSecret;

    @NotNull
    public String code;

    public String redirectUri;
}
