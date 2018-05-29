/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsItemDescriptionRepository.java
 * Date:    18-5-29 上午11:29
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

	Optional<T> findByNameAndEnable(String name, Boolean enable);

}
