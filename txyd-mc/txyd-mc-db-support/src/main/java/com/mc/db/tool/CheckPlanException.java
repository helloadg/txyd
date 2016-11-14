package com.mc.db.tool;

/**
 * Created by Mxl on 2016/9/20.
 */
public class CheckPlanException extends RuntimeException {
    private String msg;

    public CheckPlanException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
