/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UserEntity.java
 * Date:    18-5-29 上午11:19
 * Author: krun
 */

package com.krun.melons.entity;

import com.krun.melons.commons.Commons;
import com.krun.melons.commons.entity.EnableField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体
 *
 * @author krun
 * @date 2018/05/29
 */
@Entity
@Getter
@Setter
public class UserEntity extends EnableField {

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<RoleEntity> roles = new ArrayList<>();

	@Column
	private String token;

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof UserEntity) {
			return super.equals(obj)
					&& (username != null && username.equals(((UserEntity) obj).getUsername()))
					&& (password != null && password.equals(((UserEntity) obj).getPassword()))
			        && (roles != null && roles.equals(((UserEntity) obj).getRoles()))
					&& (token != null && token.equals(((UserEntity) obj).getToken()));
		}
		return super.equals(obj);
	}

	private boolean isRolesNotEmpty() {
		return !Commons.IS_LIST_EMPTY.test(roles);
	}

	public boolean hasRole(RoleEntity role) {
		return isRolesNotEmpty() && roles.contains(role);
	}

	public boolean hasRoles(List<RoleEntity> roles) {
		final List<RoleEntity> selfRoles = this.roles;
		return isRolesNotEmpty() && roles.stream().filter(selfRoles::contains).count() == roles.size();
	}

	public boolean hasPermission(PermissionEntity permission) {
		return isRolesNotEmpty() && roles.stream().anyMatch(roleEntity -> roleEntity.hasPermission(permission));
	}
}
