/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      DepartmentRepository.java
 * Date:    18-5-29 上午11:56
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsItemDescriptionRepository;
import com.krun.melons.entity.DepartmentEntity;
import com.krun.melons.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门仓库
 *
 * @author krun
 * @date 2018/05/29
 */
@Repository
public interface DepartmentRepository extends MelonsItemDescriptionRepository<DepartmentEntity> {

	/**
	 * 查询含有特定用户的部门
	 * @param user 用户
	 * @return 符合条件的结果
	 */
	List<DepartmentEntity> findByUsersContaining(UserEntity user);

}
