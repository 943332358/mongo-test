package org.yx.mongotest.authorization.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
public class Role {
    private String roleName;

    private List<Permissions> permissions;

    @Data
    static class Permissions {
        /**
         * 模块权限
         */
        private String url;

        private String name;

        /**
         * 按钮权限
         */
        private List<String> button;


    }
}
