package com.light.framework.shiro.web.filter;

import com.light.common.constant.Constants;
import com.light.common.utils.MessageUtils;
import com.light.common.utils.StringUtil;
import com.light.framework.manager.AsyncManager;
import com.light.framework.manager.factory.AsyncFactory;
import com.light.framework.util.ShiroUtils;
import com.light.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 退出过滤器
 * @author ligz
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response){
        try {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            SysUser user = ShiroUtils.getSysUser();
            if (ObjectUtils.allNotNull(user)) {
                String loginName = user.getLoginName();
                // 记录用户退出日志
                AsyncManager.getAsyncManager().execute(AsyncFactory.recordloginLog(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
            }
            // 退出登录
            subject.logout();
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Encountered session exception during logout.  This can generally safely be ignored." , e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (StringUtil.isNotEmpty(url)) {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}
