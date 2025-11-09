package com._4.javasocketchat.config;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com._4.javasocketchat.service.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtTokenFilter extends OncePerRequestFilter{
    private final JwtTokenService jwtTokenService;

    public JwtTokenFilter(JwtTokenService jwtTokenService){
        this.jwtTokenService = jwtTokenService;
    }

    // private static final String[] WHITELIST_URLs = {
    //     "/api/signup",
    //     "/api/signin"
    // };

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, java.io.IOException{
        String url = request.getRequestURI();
        System.out.println(url);
        if(url.equals("/api/signin") || url.equals("/api/signup")){
            System.out.println("白名单放行");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("进行token认证");
        String token = getToken(request);
        if(token!=null && jwtTokenService.validateToken(token)){
            System.out.println("token认证成功");
            filterChain.doFilter(request, response);
        }else{
            System.out.println("token认证失败");
            sendErrorResponse(response, 401, "token认证失败");
        }
    }

    private String getToken(HttpServletRequest request){
        String authorization = request.getHeader("authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            return authorization.substring(7);
        }
        return null;
    }

    // private boolean isWhite(String url){
    //     return false;
    // }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("status", status);
        errorDetails.put("error", "Unauthorized");
        errorDetails.put("message", message);
        errorDetails.put("path", "当前请求路径");
        
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
        response.getWriter().flush();
    }
}
