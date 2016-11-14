package com.mc.common.utils;


import com.mc.common.param.UserInfo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * 
 * 
 * Title: SSOUtils.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 * @author undyliu 2015年12月3日
 */
public class SSOUtils {

    /**
     * 获取当前请求的用户对象
     * 
     * @author undyliu
     * @date 2015年12月3日
     * @return
     */
    public static UserInfo getCurrentUserInfo() {
        return (UserInfo) RequestContextHolder.currentRequestAttributes().getAttribute(UserInfo.KEY_USER_INFO, RequestAttributes.SCOPE_REQUEST);
    }
}
