package com.ecommerce.common.exception;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@SuppressWarnings("serial")
public class InvalidInputException extends ECommerceException {

	public InvalidInputException() {}

	public InvalidInputException(final String message) {
		super(message);
	}

	public InvalidInputException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(final Throwable cause) {
		super(cause);
	}

}
