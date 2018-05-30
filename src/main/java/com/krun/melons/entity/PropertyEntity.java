/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PropertyEntity.java
 * Date:    18-5-30 上午9:24
 * Author: krun
 */

package com.krun.melons.entity;

import com.krun.melons.commons.entity.ItemDescriptionFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Melons 全局属性
 *
 * @author krun
 * @date 2018/05/30
 */
@Entity(name = "property")
@Getter
@Setter
public class PropertyEntity extends ItemDescriptionFields {

	@Column(name = "property_key", unique = true, nullable = false)
	private String key;

	@Column
	private String value;

}
