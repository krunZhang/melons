/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionService.java
 * Date:    18-5-29 下午4:17
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.service.MelonsItemDescriptionService;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.repository.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 权限服务接口
 *
 * @author krun
 * @date 2018/05/29
 */
public interface PermissionService extends MelonsItemDescriptionService<PermissionEntity, PermissionRepository> {

	/**
	 * 设置查询条件为 permission.enable = ${enable}
	 */
	Function<Boolean, Specification<PermissionEntity>> ENABLE_SPECIFICATION = enable ->
			(root, query, cb) -> cb.equal(root.get("enable").as(Boolean.class), enable);

	/**
	 * 查询所有启用的权限
	 * @return 启用的所有权限
	 */
	default List<PermissionEntity> findAllEnable () {
		return getRepository().findAll(ENABLE_SPECIFICATION.apply(true));
	}

	/**
	 * 查询所有禁用的权限
	 * @return 禁用的所有权限
	 */
	default List<PermissionEntity> findAllNotEnable() {
		return getRepository().findAll(ENABLE_SPECIFICATION.apply(false));
	}

	/**
	 * 查询所有启用的权限
	 * @return 启用的所有权限
	 */
	default Page<PermissionEntity> findAllEnable (Pageable pageable) {
		return findAll(ENABLE_SPECIFICATION.apply(true), pageable);
	}

	/**
	 * 分页查询所有禁用的权限
	 * @return 禁用的所有权限
	 */
	default Page<PermissionEntity> findAllNotEnable(Pageable pageable) {
		return findAll(ENABLE_SPECIFICATION.apply(false), pageable);
	}

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
