/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      Payload.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.payload;

import java.lang.reflect.Field;

public class Payload {

	public boolean allNull() {
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.get(this) != null) {
					return false;
				}
			}
		} catch (IllegalAccessException ignore) { }
		return true;
	}

}
