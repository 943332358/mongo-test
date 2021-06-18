package org.yx.mongotest.websocket;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
public class OutputMessage {
    private String from;
    private String text;
    private String time;
}
