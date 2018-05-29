/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      EntityNotFoundException.java
 * Date:    18-5-29 下午2:57
 * Author: krun
 */

package com.krun.melons.commons.exception;

/**
 * 无法找到实体时抛出此异常，常见于 {@link com.krun.melons.commons.service.MelonsService} 中多数 <code>xxxOrThrow</code> 方法。
 */
public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException (String message) {
		super(message);
	}
}
