package com.mc.common.param;

import java.io.Serializable;
import java.util.Map;

public class UserInfo implements Serializable {

    public static final String KEY_USER_INFO = "sso.userInfo";

    private static final long serialVersionUID = -3613085345070870877L;

    private User user;
    
    private Role role;

    private Map<String, Role> roles;

    private int currentCityId;
    
   

    public UserInfo(User user, Map<String, Role> roles, int currentCityId) {
        super();
        this.user = user;
        this.roles = roles;
        this.currentCityId = currentCityId;
    }
    
    public UserInfo(User user,Role role, int currentCityId) {
    	super();
        this.user = user;
        this.role = role;
        this.currentCityId = currentCityId;
     }

    public User getUser() {
        return user;
    }

    public Map<String, Role> getRoles() {
        return roles;
    }

    public int getCurrentCityId() {
        return currentCityId;
    }

	public Role getRole() {
		return role;
	}

}
