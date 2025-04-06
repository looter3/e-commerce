package com.ecommerce.exception;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@SuppressWarnings("serial")
public class NotFoundException extends ECommerceException {

	public NotFoundException() {}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}

}
