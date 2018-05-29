/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionEntity.java
 * Date:    18-5-29 下午5:58
 * Author: krun
 */

package com.krun.melons.entity;

import com.krun.melons.commons.entity.ItemDescriptionFields;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 权限实体
 *
 * @author krun
 * @date 2018/05/29
 */
@Entity(name = "permission")
@Getter
@Setter
public class PermissionEntity extends ItemDescriptionFields {

	@Column(nullable = false)
	private String uri;

	@Column
	@Enumerated(EnumType.STRING)
	private RequestMethod method;

	/**
	 * 是否拦截此请求方式下的接口，默认为 false
	 */
	@Column(nullable = false, columnDefinition = "bit(1) default 0")
	private Boolean intercept;

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof PermissionEntity) {
			return super.equals(obj)
					&& (uri != null && uri.equals(((PermissionEntity) obj).getUri()))
					&& (method != null && method.equals(((PermissionEntity) obj).getMethod()));
		}
		return super.equals(obj);
	}
}
