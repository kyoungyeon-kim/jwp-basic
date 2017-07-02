package next.exception;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {
	public DataAccessException() {
		super();
	}

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
