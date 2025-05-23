package com.ecommerce.exception;

/**
 * @author Lorenzo Leccese
 *
 *         15 feb 2025
 *
 */
public class EventProcessingException extends RuntimeException {

	private static final long serialVersionUID = -4300162986667980236L;

	public EventProcessingException() {}

	public EventProcessingException(final String message) {
		super(message);
	}

	public EventProcessingException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public EventProcessingException(final Throwable cause) {
		super(cause);
	}

}
