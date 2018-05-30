/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionService.java
 * Date:    18-5-30 下午2:38
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.service.MelonsItemDescriptionService;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.repository.PermissionRepository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * 权限服务接口
 *
 * @author krun
 * @date 2018/05/29
 */
public interface PermissionService extends MelonsItemDescriptionService<PermissionEntity, PermissionRepository> {

	/**
	 * 根据 uri 和请求方式查找权限实体。
	 *
	 * @param uri    查询条件
	 * @param method 查询条件
	 *
	 * @return 符合条件的实体，用 <code>Optional</code> 包裹，可能值为空。
	 */
	default Optional<PermissionEntity> findByUriAndMethod (String uri, RequestMethod method) {
		return getRepository().findByUriAndMethod(uri, method);
	}

	/**
	 * 根据 uri 和请求方式查找权限实体，不存在时抛出 {@link EntityNotFoundException} 异常。
	 *
	 * @param uri    查询条件
	 * @param method 查询条件
	 *
	 * @return 符合条件的实体
	 */
	default PermissionEntity findByUriAndMethodOrThrow (String uri, RequestMethod method) {
		return findByUriAndMethod(uri, method).orElseThrow(() -> new EntityNotFoundException("找不到权限: uri: " + uri + ", method: " + method.name()));
	}

	/**
	 * 根据 uri 和请求方式、启用状态查找权限实体。
	 *
	 * @param uri    查询条件
	 * @param method 查询条件
	 * @param enable 启用状态
	 *
	 * @return 符合条件的实体，用 <code>Optional</code> 包裹，可能值为空。
	 */
	default Optional<PermissionEntity> findByUriAndMethodAndEnable (String uri, RequestMethod method, Boolean enable) {
		return getRepository().findByUriAndMethodAndEnable(uri, method, enable);
	}

	/**
	 * 根据 uri 和请求方式、启用状态查找权限实体。不存在时抛出 {@link EntityNotFoundException} 异常。
	 *
	 * @param uri    查询条件
	 * @param method 查询条件
	 * @param enable 启用状态
	 *
	 * @return 符合条件的实体
	 */
	default PermissionEntity findByUriAndMethodAndEnableOrThrow (String uri, RequestMethod method, Boolean enable) {
		return findByUriAndMethodAndEnable(uri, method, enable).orElseThrow(() -> new EntityNotFoundException("找不到权限: uri: " + uri + ", method: " + method.name()));
	}


}
