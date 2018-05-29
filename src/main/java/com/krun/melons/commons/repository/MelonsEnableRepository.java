/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsEnableRepository.java
 * Date:    18-5-29 上午11:29
 * Author: krun
 */

package com.krun.melons.commons.repository;


import com.krun.melons.commons.entity.EnableField;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 对应 {@link EnableField} 字段的实体
 * @param <T> {@link EnableField} 的子类
 *
 * @author krun
 * @date 2018/05/29
 */
@NoRepositoryBean
public interface MelonsEnableRepository<T extends EnableField> extends MelonsUUIDRepository<T> {}
