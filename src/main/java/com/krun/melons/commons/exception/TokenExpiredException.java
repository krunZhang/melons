/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      TokenExpiredException.java
 * Date:    18-5-30 上午9:23
 * Author: krun
 */

package com.krun.melons.commons.exception;

/**
 * Token 过期异常
 *
 * @author krun
 * @date 2018/05/30
 */
public class TokenExpiredException extends RuntimeException {

	public TokenExpiredException () {
		super("Token 已过期!");
	}

}
