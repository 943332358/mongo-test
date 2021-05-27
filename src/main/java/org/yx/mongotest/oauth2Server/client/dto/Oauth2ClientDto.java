package org.yx.mongotest.oauth2Server.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import org.yx.mongotest.oauth2Server.client.entity.Oauth2Client;

/**
 * @author yangxin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Oauth2ClientDto extends Oauth2Client {

}
