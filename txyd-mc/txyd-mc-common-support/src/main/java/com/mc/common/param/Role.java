package com.mc.common.param;

import java.io.Serializable;

/**
 * 
 * 
 * Title: Role.java<br>
 * Description: <br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 北京云杉世界信息技术有限公司<br>
 * 
 * @author undyliu 2015年12月2日
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 4193816954170176267L;

    private String id;
    private String name;

    public Role() {
        super();
    }

    public Role(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}