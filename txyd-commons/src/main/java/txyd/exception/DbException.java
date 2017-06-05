package txyd.exception;

/**
 * Exception
 * 描述：无权限
 *
 * @author Administrator
 */
public class DbException extends RuntimeException implements BaseException {
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}
	
	public DbException(String msg, Throwable t) {
		super(msg, t);
	}
	
	
	public int getErrorCode() {
		return ExceptionCode.db.code;
	}
	
}
