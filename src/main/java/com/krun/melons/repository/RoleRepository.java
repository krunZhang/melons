/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      RoleRepository.java
 * Date:    18-5-29 上午11:56
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsItemDescriptionRepository;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色仓库
 *
 * @author krun
 * @date 2018/05/29
 */
@Repository
public interface RoleRepository extends MelonsItemDescriptionRepository<RoleEntity> {

	/**
	 * 查询包含特定权限的实体
	 * @param permission 特定权限
	 * @return 符合条件的所有角色
	 */
	List<RoleEntity> findByPermissionsContaining(PermissionEntity permission);
}
