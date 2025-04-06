package com.ecommerce.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Data
@RequiredArgsConstructor
public class HttpErrorInfo {

	private final ZonedDateTime timestamp;
	private final String path;
	private final HttpStatus httpStatus;
	private final String message;

}
