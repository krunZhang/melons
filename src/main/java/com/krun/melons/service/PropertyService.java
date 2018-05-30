/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PropertyService.java
 * Date:    18-5-30 上午9:36
 * Author: krun
 */

package com.krun.melons.service;

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
}
