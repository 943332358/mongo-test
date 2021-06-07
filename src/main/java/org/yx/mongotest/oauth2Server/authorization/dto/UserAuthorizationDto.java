package org.yx.mongotest.oauth2Server.authorization.dto;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author yangxin
 */
@Data
public class UserAuthorizationDto {
    public String clientId;

    public boolean userImg;

    public boolean userPermissions;

    public boolean userInfo;

    public String username;

    public List<String> getPermissions() {
        List<String> list = Lists.newArrayList();
        if (userImg) {
            list.add("userImg");
        }
        if (userPermissions) {
            list.add("userPermissions");
        }
        if (userInfo) {
            list.add("userInfo");
        }
        return list;
    }
}
