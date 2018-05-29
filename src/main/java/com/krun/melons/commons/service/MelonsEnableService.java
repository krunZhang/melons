/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsEnableService.java
 * Date:    18-5-29 下午4:26
 * Author: krun
 */

package com.krun.melons.commons.service;

import com.krun.melons.commons.entity.EnableField;
import com.krun.melons.commons.repository.MelonsEnableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 对应 {@link MelonsEnableRepository } 的服务接口
 * @param <T> 实体
 * @param <R> 实体仓库
 *
 * @author krun
 * @date 2018/05/29
 */
public interface MelonsEnableService<T extends EnableField, R extends MelonsEnableRepository<T>>
		extends MelonsUUIDService<T, R> {

	/**
	 * 获取关于启用状态的查询条件
	 * @param enable 启用状态
	 * @return 构造好的查询条件
	 */
	default Specification<T> getEnableSpecification(Boolean enable) {
		return (root, query, cb) -> cb.equal(root.get("enable").as(Boolean.class), enable);
	}

	/**
	 * 查询所有启用的实体
	 * @return 启用的所有实体
	 */
	default List<T> findAllEnable () {
		return getRepository().findAll(getEnableSpecification(true));
	}

	/**
	 * 查询所有禁用的实体
	 * @return 禁用的所有实体
	 */
	default List<T> findAllNotEnable() {
		return getRepository().findAll(getEnableSpecification(false));
	}

	/**
	 * 查询所有启用的实体
	 * @return 启用的所有实体
	 */
	default Page<T> findAllEnable (Pageable pageable) {
		return findAll(getEnableSpecification(true), pageable);
	}

	/**
	 * 分页查询所有禁用的实体
	 * @return 禁用的所有实体
	 */
	default Page<T> findAllNotEnable(Pageable pageable) {
		return findAll(getEnableSpecification(false), pageable);
	}

}
