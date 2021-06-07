package org.yx.mongotest.oauth2Server.authorization.enums;

/**
 * 授权类型
 *
 * @author yangxin
 */
public enum GrantType {
    /**
     * 获取TOKEN
     */
    ACCESS_TOKEN("accessToken", "获取TOKEN"),
    /**
     * 刷新TOKEN
     */
    REFRESH_TOKEN("refreshToken", "刷新TOKEN");

    private String code;
    private String name;

    GrantType(String code, String name) {
    }
}
