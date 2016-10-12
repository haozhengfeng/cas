package org.haozf.identity.user.exception;

/**
 * 用户异常基类
 * 2016年6月28日
 * @author haozhengfeng
 */
public class UserException extends RuntimeException {
	static final long serialVersionUID = -7034897190745766939L;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
