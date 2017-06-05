package txyd.exception;

import java.util.Arrays;

/**
 * 异常码值
 *
 * @author Administrator
 */
public enum ExceptionCode {
	system(1000,"系统异常码"),
	busyness(1001,"系统繁忙异常码"),
	invalidParam(1002,"非法参数异常码"),
	resourceExist(1003,"资源已存在异常码"),
	resourceNotExist(1004,"资源不存在异常码"),
	notResponse(1005,"系统未响应异常码"),
	withoutAuthority(1006,"无权限异常码"),
	serializer(1007,"序列化异常码"),
	rpc(1008,"远程调用异常码"),
	withoutLogin(1009,"无权限异常码"),
	web(1010,"web异常码"),
	db(1011,"db异常码"),
	;
	
	
	
	public int code;
	public String dec;
	
	ExceptionCode(int status, String dec){
		this.code = status;
		this.dec = dec;
	}
	
	public static String getStateDec(int code){
		ExceptionCode[] values = ExceptionCode.values();
		for(ExceptionCode value : values){
			if(value.code == code)
				return value.dec;
		}
		return "--";
	}
	
	public static void main(String[] args) {
		Arrays.stream(ExceptionCode.values()).forEach(e->{
			System.out.println("self:{self},status:{code},ordinal:{ordinal}"
					.replace("{self}",e+"")
					.replace("{code}",e.code+"")
					.replace("{ordinal}",e.ordinal()+""));
		});
	}
	
}
