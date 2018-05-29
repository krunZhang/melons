/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UUIDField.java
 * Date:    18-5-29 上午10:30
 * Author: krun
 */

package com.krun.melons.commons.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 任何继承此类的实体都将获得字段:
 * <code>id:String(UUID)</code> 类型的主键。
 *
 * @author krun
 * @date 2018/05/29
 */
@MappedSuperclass
@Getter
@Setter
public class UUIDField {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof UUIDField) {
			return id.equals(((UUIDField) obj).getId());
		}
		return super.equals(obj);
	}
}
