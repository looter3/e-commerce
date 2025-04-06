package com.ecommerce.exception;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@SuppressWarnings("serial")
public class ECommerceException extends RuntimeException {

	public ECommerceException() {}

	public ECommerceException(final String message) {
		super(message);
	}

	public ECommerceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ECommerceException(final Throwable cause) {
		super(cause);
	}

}
