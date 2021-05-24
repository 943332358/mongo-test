package org.yx.mongotest.oauth2Server.client.entity;

/**
 * @author yangxin
 */

public enum ClientState {
    /**
     * 启用
     */
    ENABLE(0, "启用"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    ClientState(int id, String name) {
    }

}
