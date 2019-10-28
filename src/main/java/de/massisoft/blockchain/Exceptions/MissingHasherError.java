package de.massisoft.blockchain.Exceptions;

public class MissingHasherError extends Error {

	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "Please provide a Hasher.";
	
	public MissingHasherError() {
		super(ERROR_MESSAGE);
	}
}
