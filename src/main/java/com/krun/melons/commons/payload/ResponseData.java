/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      ResponseData.java
 * Date:    18-5-29 下午5:57
 * Author: krun
 */

package com.krun.melons.commons.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData <E> {

	private int code;
	private String message;
	private E data;

	public static <E> ResponseDataBuilder<E> ok() {
		return create(HttpStatus.OK);
	}

	public static <E> ResponseDataBuilder<E> ok(String message) {
		return create(HttpStatus.OK, message);
	}

	public static <E> ResponseDataBuilder<E> unauthorized() {
		return create(HttpStatus.UNAUTHORIZED);
	}

	public static <E> ResponseDataBuilder<E> unauthorized(String message) {
		return create(HttpStatus.UNAUTHORIZED, message);
	}

	public static <E> ResponseDataBuilder<E> badRequest() {
		return create(HttpStatus.BAD_REQUEST);
	}

	public static <E> ResponseDataBuilder<E> badRequest(String message) {
		return create(HttpStatus.BAD_REQUEST, message);
	}

	private static <E> ResponseDataBuilder<E> create(HttpStatus status) {
		return create(status, status.getReasonPhrase());
	}

	private static <E> ResponseDataBuilder<E> create(HttpStatus status, String message) {
		return ResponseData.<E>builder()
				.code(status.value())
				.message(message);
	}

}
