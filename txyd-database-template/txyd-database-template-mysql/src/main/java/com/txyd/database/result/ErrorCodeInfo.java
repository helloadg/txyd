package com.txyd.database.result;
/**
 * java类型
 * @author     
 *
 */
public enum ErrorCodeInfo implements ResultEnum {
	SYSTEM_EXCEPTION(1001,"system_exception"),
	INVALID_PARAM(1101,"invalid_param"),
	RESOURCE_NOT_EXIST(1102,"resource_not_exist"),
	RESOURCE_EXIST(1103,"resource_exist"),
	UNKNOWN_EXCEPTION(1104,"unknown_exception"),
	NOT_RESPONSE_EXCEPTION(1500,"not_response_exception"),
	WITHOUT_AUTHORITY_EXCEPTION(1401,"without_authority_exception"),
	BUSINESS_EXCEPTION(3000,"business_exception");
	
	// 成员变量
    private String message;//枚举值的自定义名
    private int code;//枚举值的索引值

    // 构造方法
    private ErrorCodeInfo(int code,String message) {
        this.code=code;
        this.message = message;
    }

    /**
     * 
     * @Description 获得枚举的名称
     * @author     
     * @return
     */
    @Override
    public String toString() {
    	return super.toString();
    }


    /**
     * 
     * @Description 获得枚举值的全路径，即："类名.值名"
     * @author     
     * @return
     */
    @Override
    public String getPath()
    {
    	return this.getClass().getSimpleName()+"."+this.toString();
    }
    
    /**
     * 
     * @Description 获得枚举的索引
     * @author     
     * @return
     */
	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
    public static void main(String[] args){
    	System.out.println(ErrorCodeInfo.INVALID_PARAM);
    	System.out.println(ErrorCodeInfo.INVALID_PARAM.getCode());
    	System.out.println(ErrorCodeInfo.INVALID_PARAM.getMessage());
    	System.out.println(ErrorCodeInfo.INVALID_PARAM.getPath());
    	System.out.println(ErrorCodeInfo.INVALID_PARAM.toString());
    }
}
