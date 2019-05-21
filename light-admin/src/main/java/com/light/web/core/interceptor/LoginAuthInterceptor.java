package com.light.web.core.interceptor;

import com.light.common.annotation.LoginAuth;
import com.light.framework.util.ShiroUtils;
import com.light.system.domain.SysUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 已登录权限验证拦截器 备注：通过{@link LoginAuth}配合使用
 * author:ligz
 */
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
                SysUser loginUser = ShiroUtils.getSysUser();
                return ObjectUtils.allNotNull(loginUser);
            }
        }
        return true;
    }
}
