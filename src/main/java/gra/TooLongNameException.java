package gra;

public class TooLongNameException extends RuntimeException {
	public TooLongNameException() { super(); }
	  public TooLongNameException(String message) { super(message); }
	  public TooLongNameException(String message, Throwable cause) { super(message, cause); }
	  public TooLongNameException(Throwable cause) { super(cause); }
}
