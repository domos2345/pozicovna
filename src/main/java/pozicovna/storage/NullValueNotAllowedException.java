package pozicovna.storage;

public class NullValueNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 71022948595665092L;

	public NullValueNotAllowedException(String message) {
		super(message);
	}

}
