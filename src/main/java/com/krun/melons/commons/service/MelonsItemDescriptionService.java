/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsItemDescriptionService.java
 * Date:    18-5-29 下午3:12
 * Author: krun
 */

package com.krun.melons.commons.service;

import com.krun.melons.commons.entity.ItemDescriptionFields;
import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.repository.MelonsItemDescriptionRepository;

import java.util.Optional;

/**
 * 对应 {@link MelonsItemDescriptionRepository } 的服务接口
 * @param <T> 实体
 * @param <R> 实体仓库
 *
 * @author krun
 * @date 2018/05/29
 */
public interface MelonsItemDescriptionService<T extends ItemDescriptionFields, R extends MelonsItemDescriptionRepository<T>>
		extends MelonsEnableService<T, R> {

	/**
	 * 根据 <code>name</code> 获取实体
	 * @param name 实体名称字段值
	 * @return 符合条件的实体，用 <code>Optional</code> 包裹，可能值为空。
	 */
	default Optional<T> findByName(String name) {
		return getRepository().findByName(name);
	}

	/**
	 * 根据 <code>name</code> 获取实体，不存在时会抛出 {@link EntityNotFoundException} 异常。
	 * @param name 实体名称字段值
	 * @return 符合条件的实体
	 */
	default T findByNameOrThrow(String name) {
		return getRepository().findByName(name).orElseThrow(() -> new EntityNotFoundException("找不到实体: name: " + name));
	}

	/**
	 * 根据 <code>name</code> 和启用状态获取实体
	 * @param name 实体名称字段值
	 * @param enable 实体启用状态
	 * @return 符合条件的实体，用 <code>Optional</code> 包裹，可能值为空。
	 */
	default Optional<T> findByNameAndEnable(String name, Boolean enable) {
		return getRepository().findByNameAndEnable(name, enable);
	}

	/**
	 * 根据 <code>name</code> 和启用状态获取实体，不存在时会抛出 {@link EntityNotFoundException} 异常。
	 * @param name 实体名称字段值
	 * @return 符合条件的实体
	 */
	default T findByNameAndEnableOrThrow(String name, Boolean enable) {
		return getRepository().findByNameAndEnable(name, enable)
		                      .orElseThrow(() -> new EntityNotFoundException("找不到实体: name: " + name + ", enable: " + enable));
	}
}
