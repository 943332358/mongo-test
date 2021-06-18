package org.yx.mongotest.leetcode;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.yx.mongotest.common.function.Function;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangxin
 * 代理redis服务
 */
@Slf4j
public class Server {

    public static void main(String[] args) throws Exception {
        // 监听指定端口
        var ss = new ServerSocket(6666);
        log.info("server is running...");

        Socket sock = ss.accept();
        InputStream input = sock.getInputStream();
        OutputStream output = sock.getOutputStream();
        handle(input, output);
    }


    static Function<InputStream, Integer> function = inputStream -> {
        int available;
        while (true) {
            if ((available = inputStream.available()) > 0) {
                break;
            }
        }
        return available;
    };

    private static void handle(InputStream input, OutputStream output) throws Exception {
        // 连接redis server
        try (var socket = new Socket("localhost", 6379);
             var outputStream = socket.getOutputStream();
             var inputStream = socket.getInputStream()) {


            // 获取redis client消息
            byte[] bytes;
            while (input.read(bytes = new byte[function.apply(input)]) != -1) {
                log.info("redis client：{}", new String(bytes));

                // 向redis server输出消息
                outputStream.write(bytes);
                outputStream.flush();

                // 获取redis server返回的消息
                byte[] serverBytes;
                inputStream.read(serverBytes = new byte[function.apply(inputStream)]);

                log.info("redis server：{}", new String(serverBytes));

                // 向redis客户端返回server消息
                output.write(serverBytes);
                output.flush();
            }


        }
    }

}