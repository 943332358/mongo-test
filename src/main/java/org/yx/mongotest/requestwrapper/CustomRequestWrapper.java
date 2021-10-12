package org.yx.mongotest.requestwrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Optional;

/**
 * @author A
 */
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(request.getInputStream());

        String pageSize = Optional.ofNullable(jsonNode.get("pageSize")).map(m -> {
                    if (m.asInt() > 40) {
                        return "40";
                    }
                    return m.asText();
                })
                .orElse("");

        ((ObjectNode) jsonNode).put("pageSize", pageSize);


        body = jsonNode.toPrettyString();

    }

    @Override
    public String[] getParameterValues(String name) {
        if ("pageSize".equals(name)) {
            return Optional.ofNullable(getParameter(name)).map(m -> {
                if (Integer.parseInt(m) > 40) {
                    return new String[]{"40"};
                }
                return new String[]{m};
            }).orElse(new String[]{""});

        }

        return super.getParameterValues(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return super.getReader();
    }
}
