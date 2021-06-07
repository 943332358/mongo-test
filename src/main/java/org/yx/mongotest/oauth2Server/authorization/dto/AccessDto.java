package org.yx.mongotest.oauth2Server.authorization.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
public class AccessDto {
    public String token;

    public String refreshToken;
}
