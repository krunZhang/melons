/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsService.java
 * Date:    18-5-29 下午3:17
 * Author: krun
 */

package com.krun.melons.commons.service;

import com.alibaba.fastjson.JSONObject;
import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.repository.MelonsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * 提供基础的 Service 支持
 *
 * @author krun
 * @date 2018/05/29
 */
public interface MelonsService<T, ID, R extends MelonsRepository<T, ID>> {

	/**
	 * 获取仓库实例以与其他默认方法进行交互
	 * @return 仓库实例
	 */
	R getRepository();

	/**
	 * 获取指定 ID 对应的实体
	 * @param id 要查询的 ID
	 * @return 如果存在该实体，则返回值为 <code>Optional.of(...)</code>，否则为 <code>Optional.ofNullable(null)</code>。
	 */
	default Optional<T> findById(ID id) {
		return getRepository().findById(id);
	}

	/**
	 * 获取指定条件对应的实体
	 * @param specification 要查询的条件
	 * @return 如果存在该实体，则返回值为 <code>Optional.of(...)</code>，否则为 <code>Optional.ofNullable(null)</code>。
	 */
	default Optional<T> findOne(Specification<T> specification) {
		return getRepository().findOne(specification);
	}

	/**
	 * 获取指定条件对应的实体，如果不存在则会抛出 {@link EntityNotFoundException} 异常。
	 * @param specification 要查询的条件
	 * @return 符合条件的实体
	 */
	default T findOneOrThrow(Specification<T> specification) {
		return findOne(specification)
		                      .orElseThrow(() -> new EntityNotFoundException("找不到实体: " + JSONObject.toJSONString(specification)));
	}

	/**
	 * 获取指定 ID 对应的实体，如果不存在则会抛出 {@link EntityNotFoundException} 异常。
	 * @param id 要查询的 id
	 * @return 符合条件的实体
	 */
	default T findByIdOrThrow(ID id) {
		return findById(id).orElseThrow(() -> new EntityNotFoundException("找不到实体: id: " + id));
	}

	/**
	 * 分页查询所有记录
	 * @param pageable 分页条件
	 * @return 分页后的数据
	 */
	default Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	/**
	 * 分页查询所有符合条件的记录
	 * @param specification 要查询的条件
	 * @param pageable 分页条件
	 * @return 符合查询条件的分页数据
	 */
	default Page<T> findAll(Specification<T> specification, Pageable pageable) {
		return getRepository().findAll(specification, pageable);
	}

	/**
	 * 获取记录数
	 * @return 实体数量
	 */
	default long count() {
		return getRepository().count();
	}

	/**
	 * 获取符合条件的记录数
	 * @param specification 要查询的条件
	 * @return 满足条件的实体数量
	 */
	default long count(Specification<T> specification) {
		return getRepository().count(specification);
	}

	/**
	 * 保存给定实体
	 * @param entity 要保存的实体
	 * @return 保存/更新后的实体
	 */
	default T save(T entity) {
		return getRepository().saveAndFlush(entity);
	}

	/**
	 * 删除给定的实体
	 * @param entity 要删除的实体
	 */
	default void delete(T entity) {
		getRepository().delete(entity);
	}

	/**
	 * 检查给定 ID 对应的实体是否存在
	 * @param id 要坚持的 id
	 * @return 检查结果
	 */
	default boolean existById(ID id) {
		return getRepository().existsById(id);
	}

}
