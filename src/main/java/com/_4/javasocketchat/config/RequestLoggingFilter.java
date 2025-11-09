package com._4.javasocketchat.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            try {
                logRequest(wrappedRequest, wrappedResponse, duration);
            } catch (Exception e) {
                logger.warn("Failed to log request/response", e);
            } finally {
                // 必须在读取响应体后把内容写回客户端
                wrappedResponse.copyBodyToResponse();
            }
        }
    }

    private void logRequest(ContentCachingRequestWrapper request,
                            ContentCachingResponseWrapper response, long duration) {

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String clientIP = getClientIP(request);

        // 请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        if (headerNames != null) {
            Collections.list(headerNames).forEach(name -> headers.put(name, request.getHeader(name)));
        }

        // 请求体 / 响应体
        String requestBody = getRequestBody(request);
        int status = response.getStatus();
        String responseBody = getResponseBody(response);

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=== 请求信息 ===\n")
                 .append("方法: ").append(method).append("\n")
                 .append("URL: ").append(uri).append("\n")
                 .append("查询参数: ").append(queryString).append("\n")
                 .append("客户端IP: ").append(clientIP).append("\n")
                 .append("请求头: ").append(headers).append("\n")
                 .append("请求体: ").append(requestBody).append("\n")
                 .append("=== 响应信息 ===\n")
                 .append("状态码: ").append(status).append("\n")
                 .append("响应体: ").append(responseBody).append("\n")
                 .append("耗时: ").append(duration).append("ms\n")
                 .append("=================\n\n");

        logger.info(logMessage.toString());
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null && !xfHeader.isBlank()) {
            return xfHeader.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        if (content != null && content.length > 0) {
            Charset cs = charsetOrDefault(request.getCharacterEncoding());
            return new String(content, cs);
        }
        return "";
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        if (content != null && content.length > 0) {
            Charset cs = charsetOrDefault(response.getCharacterEncoding());
            return new String(content, cs);
        }
        return "";
    }

    private Charset charsetOrDefault(String encoding) {
        try {
            return (encoding == null) ? DEFAULT_CHARSET : Charset.forName(encoding);
        } catch (Exception e) {
            return DEFAULT_CHARSET;
        }
    }
}