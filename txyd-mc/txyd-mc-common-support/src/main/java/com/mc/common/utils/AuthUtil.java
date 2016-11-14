package com.mc.common.utils;

/**
 * Description: 常量类<br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉信息技术有限公司<br>
 * 
 * @author 赵义云 2015年10月28日
 */
public class AuthUtil {

    public static class AuthInfo {
        /** 系统名称 */
        public static final String MEICAI_SYSTEM_KEY = ConfigFileUtils
                .getAuthconfig("MEICAI_SYSTEM_KEY");
        /** auth地址 */
        public static final String MEICAI_AUTH_SERVER = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_SERVER");
        /** auth响应超时时间 */
        public static final String MEICAI_AUTH_TIMEOUT = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_TIMEOUT");
        /** sso域名拦截 */
        public static final String MEICAI_AUTH_SSO_DOMAIN = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_SSO_DOMAIN");
        /** token key */
        public static final String MEICAI_AUTH_TOKEN_NAME = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_TOKEN_NAME");
        /** login地址 */
        public static final String MEICAI_AUTH_LOGIN_URL = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_LOGIN_URL");
        /** logout地址 */
        public static final String MEICAI_AUTH_LOGOUT_URL = ConfigFileUtils
                .getAuthconfig("MEICAI_AUTH_LOGOUT_URL");
    }

}