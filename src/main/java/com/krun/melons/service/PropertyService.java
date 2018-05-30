/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PropertyService.java
 * Date:    18-5-30 上午9:45
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.service.MelonsItemDescriptionService;
import com.krun.melons.entity.PropertyEntity;
import com.krun.melons.repository.PropertyRepository;

import java.util.Optional;

/**
 * 全局属性服务
 *
 * @author krun
 * @date 2018/05/30
 */

public interface PropertyService extends MelonsItemDescriptionService<PropertyEntity, PropertyRepository> {

	/**
	 * 查找指定 key 的属性实体
	 * @param key 待查询的属性 key
	 * @return key 所对应的属性实体
	 */
	default Optional<PropertyEntity> findByKey(String key) {
		return getRepository().findByKey(key);
	}

	/**
	 * 查找指定 key 的属性实体，不存在时使用参数二提供的默认值，且默认值会被添加到记录中
	 * @param key 待查询的属性 key
	 * @param property 默认值
	 * @return key 对应的属性或保存后的默认值
	 */
	default PropertyEntity findByKeyOrDefault(String key, PropertyEntity property) {
		return findByKey(key)
				.orElseGet(() -> save(property));
	}

	/**
	 * 直接获取 key 对应属性的值
	 * @param key 待查询的属性 key
	 * @return 属性值
	 */
	default String getByKeyOrThrow(String key) {
		return findByKey(key).orElseThrow(() -> new EntityNotFoundException("找不到属性: key" + key))
				.getValue();
	}
}
