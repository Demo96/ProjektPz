package gra;

public class WrongPawnException extends RuntimeException {
	public WrongPawnException() { super(); }
	  public WrongPawnException(String message) { super(message); }
	  public WrongPawnException(String message, Throwable cause) { super(message, cause); }
	  public WrongPawnException(Throwable cause) { super(cause); }
}
