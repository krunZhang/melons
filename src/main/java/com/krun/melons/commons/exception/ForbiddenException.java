/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      ForbiddenException.java
 * Date:    18-5-30 上午9:23
 * Author: krun
 */

package com.krun.melons.commons.exception;

/**
 * 无权限异常
 *
 * @author krun
 * @date 2018/05/30
 */
public class ForbiddenException extends RuntimeException {

	public ForbiddenException (String message) {
		super(message);
	}
}