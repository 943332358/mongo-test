package org.yx.mongotest.requestwrapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class SimpleInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        String pageSize = request.getParameter("pageSize");
        if (Integer.parseInt(pageSize) > 40) {
            request.setAttribute("customAttribute", "value");
        }

        return true;
    }

}
