/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      ItemDescriptionFields.java
 * Date:    18-5-29 上午10:41
 * Author: krun
 */

package com.krun.melons.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 任何继承此类的实体都将获得字段:
 * <code>enable:Boolean</code> 类型的字段，默认值为 true。
 * 此外，此类还继承了 {@link EnableField}，因此也会获得其所拥有的所有字段。
 * @author krun
 * @date 2018/05/29
 */
@MappedSuperclass
@Getter
@Setter
public class ItemDescriptionFields extends EnableField {

	@Column(unique = true, nullable = false)
	private String name;

	@Column
	private String description;

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof ItemDescriptionFields) {
			return super.equals(obj)
					&& (name != null && name.equals(((ItemDescriptionFields) obj).getName()))
					&& (description != null && description.equals(((ItemDescriptionFields) obj).getDescription()));
		}
		return super.equals(obj);
	}
}
