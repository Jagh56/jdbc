package jdbc;

public class DALException extends Exception {
	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}

	@Override
	public String getMessage() {
		return "Couche DAL - " + super.getMessage();
	}
}
