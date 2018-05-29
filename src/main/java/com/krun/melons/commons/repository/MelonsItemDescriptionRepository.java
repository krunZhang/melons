/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsItemDescriptionRepository.java
 * Date:    18-5-29 上午11:34
 * Author: krun
 */

package com.krun.melons.commons.repository;

import com.krun.melons.commons.entity.ItemDescriptionFields;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * 对应 {@link ItemDescriptionFields} 实体
 * @param <T> {@link ItemDescriptionFields} 的子类
 *
 * @author krun
 * @date 2018/05/29
 */
@NoRepositoryBean
public interface MelonsItemDescriptionRepository<T extends ItemDescriptionFields> extends MelonsEnableRepository<T> {

	/**
	 * 根据条件查询实体
	 * @param name 实体名称字段值
	 * @param enable 实体启用状态
	 * @return 符合条件的实体，用 {@link Optional} 包裹，值可能为空
	 */
	Optional<T> findByNameAndEnable(String name, Boolean enable);

}
