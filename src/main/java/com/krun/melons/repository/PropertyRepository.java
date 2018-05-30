/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PropertyRepository.java
 * Date:    18-5-30 上午9:24
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsItemDescriptionRepository;
import com.krun.melons.entity.PropertyEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 全局属性仓库
 */
@Repository
public interface PropertyRepository extends MelonsItemDescriptionRepository<PropertyEntity> {

	Optional<PropertyEntity> findByKey(String key);
}
