package org.yx.mongotest.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author yangxin
 */
@Component
public class ScheduledPushMessages {
    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        Stream.of();

        simpMessagingTemplate.convertAndSend("/topic/pushmessages",
                new OutputMessage().setFrom("from")
                        .setText("text")
                        .setTime(new SimpleDateFormat("HH:mm").format(new Date())));
    }
}
