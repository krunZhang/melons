/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      RoleEntity.java
 * Date:    18-5-29 下午5:58
 * Author: krun
 */

package com.krun.melons.entity;

import com.krun.melons.commons.Commons;
import com.krun.melons.commons.entity.ItemDescriptionFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * 角色实体
 *
 * @author krun
 * @date 2018/05/29
 */
@Entity(name = "role")
@Getter
@Setter
public class RoleEntity extends ItemDescriptionFields {

	@ManyToMany(fetch = FetchType.LAZY)
	private List<PermissionEntity> permissions;

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof RoleEntity) {
			return super.equals(obj)
					&& (permissions.equals(((RoleEntity) obj).getPermissions()));
		}
		return super.equals(obj);
	}

	private boolean isPermissionsNotEmpty() {
		return ! Commons.IS_LIST_EMPTY.test(permissions);
	}

	public boolean hasPermission(PermissionEntity permission) {
		return isPermissionsNotEmpty() && permissions.contains(permission);
	}

	public boolean hasPermissions(List<PermissionEntity> permissions) {
		final List<PermissionEntity> selfPermissions = this.permissions;
		return isPermissionsNotEmpty() && permissions.stream().filter(selfPermissions::contains).count() == permissions.size();
	}
}
