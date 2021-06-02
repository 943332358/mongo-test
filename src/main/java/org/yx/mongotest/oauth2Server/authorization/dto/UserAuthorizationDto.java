package org.yx.mongotest.oauth2Server.authorization.dto;

import lombok.Data;

/**
 * @author yangxin
 */
@Data
public class UserAuthorizationDto {
    public String clientId;

    public boolean userImg;

    public boolean userPermissions;

    public boolean userInfo;
}
