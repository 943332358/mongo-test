package org.yx.mongotest.requestwrapper;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class WrapperFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        CustomRequestWrapper wrapper = new CustomRequestWrapper(httpServletRequest);

        filterChain.doFilter(wrapper, servletResponse);
    }
}
