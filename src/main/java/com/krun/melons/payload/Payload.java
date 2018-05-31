/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      Payload.java
 * Date:    18-5-31 上午9:22
 * Author: krun
 */

package com.krun.melons.payload;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Payload<T> {

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

	/**
	 * 此方法使用 AND 连接所有非空的字段值，且条件为 [字段=值]
	 * @return
	 */
	public ArrayList<Predicate> mapToPrdicates(Root<T> root, CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = new ArrayList<>();
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (field.get(this) != null) {
					predicates.add(criteriaBuilder.equal(root.get(field.getName()).as(field.getType()), field.get(this)));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return predicates;
	}

}
