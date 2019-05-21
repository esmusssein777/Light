package com.light.common.constant;

/**
 * Shiro通用常量
 * @author ligz
 */
public final class ShiroConstants {

    private ShiroConstants(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 用户名
     */
    public static final String CURRENT_USERNAME = "username" ;

    /**
     * 当前在线会话
     */
    public static final String ONLINE_SESSION = "online_session" ;

    /**
     * 验证码key
     */
    public static final String CURRENT_CAPTCHA = "captcha" ;

    /**
     * 验证码开关
     */
    public static final String CURRENT_ENABLED = "captchaEnabled" ;

    /**
     * 验证码开关
     */
    public static final String CURRENT_TYPE = "captchaType" ;

    /**
     * 验证码
     */
    public static final String CURRENT_VALIDATECODE = "validateCode" ;

    /**
     * 验证码错误
     */
    public static final String CAPTCHA_ERROR = "captchaError" ;
}
