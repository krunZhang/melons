/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsUUIDRepository.java
 * Date:    18-5-29 上午11:29
 * Author: krun
 */

package com.krun.melons.commons.repository;

import com.krun.melons.commons.entity.UUIDField;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 对应 {@link UUIDField} 主键类型的实体
 * @param <T> {@link UUIDField} 的子类
 *
 * @author krun
 * @date 2018/05/29
 */
@NoRepositoryBean
public interface MelonsUUIDRepository<T extends UUIDField> extends MelonsRepository<T, String> {}
