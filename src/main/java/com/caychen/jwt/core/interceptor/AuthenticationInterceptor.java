package com.caychen.jwt.core.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.caychen.jwt.core.annotation.TokenIgnore;
import com.caychen.jwt.core.error.CustomException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2020-03-10
 * @Describe:
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        TokenIgnore tokenIgnore = method.getAnnotation(TokenIgnore.class);
        if (tokenIgnore != null) {
            return true;
        }

        if (token == null) {
            throw new CustomException("无token，请重新登录");
        }

        DecodedJWT decode = JWT.decode(token);
        List<String> audience = decode.getAudience();
        String userId = audience.get(0);


        return super.preHandle(request, response, handler);
    }
}
