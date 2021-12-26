package com.guwave.onetest.bff.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Integer.MIN_VALUE)
public class SessionFilter extends HttpFilter {
    private static final String SESSION_ID_HEADER = "session-id";
    private static final LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public String load(String key) throws Exception {
            return null;
        }
    });

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String sessionId = request.getHeader(SESSION_ID_HEADER);
        if (StringUtils.isBlank(sessionId)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "without session-id header");
            return;
        }

        if (cache.asMap().size() > 0 && !cache.asMap().containsValue(sessionId)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "other user is using engine");
            return;
        }

        if (cache.asMap().size() == 0) {
            cache.put(sessionId, sessionId);
        }

        super.doFilter(request, response, chain);
    }
}
