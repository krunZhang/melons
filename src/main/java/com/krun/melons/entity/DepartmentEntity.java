/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      DepartmentEntity.java
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
 * 部门实体
 *
 * @author krun
 * @date 2018/05/29
 */
@Entity(name = "department")
@Getter
@Setter
public class DepartmentEntity extends ItemDescriptionFields {

	@ManyToMany(fetch = FetchType.LAZY)
	private List<UserEntity> users;

	private boolean isUsersNotEmpty() {
		return !Commons.IS_LIST_EMPTY.test(users);
	}

	public boolean hasUser(UserEntity user) {
		return isUsersNotEmpty() && users.contains(user);
	}

	public boolean hasUsers(List<UserEntity> users) {
		final List<UserEntity> selfUsers = this.users;
		return isUsersNotEmpty() && users.stream().filter(selfUsers::contains).count() == users.size();
	}
}
