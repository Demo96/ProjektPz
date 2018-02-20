package gra;

public class NoNameException extends RuntimeException {
	public NoNameException() { super(); }
	  public NoNameException(String message) { super(message); }
	  public NoNameException(String message, Throwable cause) { super(message, cause); }
	  public NoNameException(Throwable cause) { super(cause); }
}
