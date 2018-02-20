package gra;

public class UnavaibleMoveException extends RuntimeException {
	public UnavaibleMoveException() { super(); }
	  public UnavaibleMoveException(String message) { super(message); }
	  public UnavaibleMoveException(String message, Throwable cause) { super(message, cause); }
	  public UnavaibleMoveException(Throwable cause) { super(cause); }
}
