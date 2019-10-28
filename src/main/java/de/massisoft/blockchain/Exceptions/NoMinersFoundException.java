package de.massisoft.blockchain.Exceptions;

public class NoMinersFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String EXCEPTION_MESSAGE = "No miners free at the moment.";
	
	public NoMinersFoundException() {
		super(EXCEPTION_MESSAGE);
	}
}
