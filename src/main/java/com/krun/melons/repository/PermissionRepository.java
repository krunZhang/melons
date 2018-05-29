/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionRepository.java
 * Date:    18-5-29 上午11:56
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsItemDescriptionRepository;
import com.krun.melons.entity.PermissionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * 权限仓库
 *
 * @author krun
 * @date 2018/05/29
 */
@Repository
public interface PermissionRepository extends MelonsItemDescriptionRepository<PermissionEntity> {

	/**
	 * 根据参数查询权限
	 * @param uri uri
	 * @param method 请求方式
 	 * @return 符合条件的权限实体，用 {@link Optional} 包裹，可能值为空
	 */
	Optional<PermissionEntity> findByUriAndMethod(String uri, RequestMethod method);

	/**
	 * 根据参数查询权限
	 * @param uri uri
	 * @param method 请求方式
	 * @param enable 启用状态
	 * @return 符合条件的权限实体，用 {@link Optional} 包裹，可能值为空
	 */
	Optional<PermissionEntity> findByUriAndMethodAndEnable(String uri, RequestMethod method, Boolean enable);

}
