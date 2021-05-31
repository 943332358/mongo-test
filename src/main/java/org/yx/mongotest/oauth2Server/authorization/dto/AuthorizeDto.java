package org.yx.mongotest.oauth2Server.authorization.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
public class AuthorizeDto {

    public String clientId;

    public String redirectUri;

    /**
     * 权限范围列表
     */
    public String scope;

    /**
     * 不可猜测的随机字符串。它用于防止跨站点请求伪造攻击。
     */
    public String state;

    /**
     * 在OAuth流程中，是否向未认证的用户提供注册的选项。默认值为true。
     */
    public boolean allowSignup;

}
