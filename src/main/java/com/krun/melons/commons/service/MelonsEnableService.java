/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsEnableService.java
 * Date:    18-5-29 下午3:12
 * Author: krun
 */

package com.krun.melons.commons.service;

import com.krun.melons.commons.entity.EnableField;
import com.krun.melons.commons.repository.MelonsEnableRepository;

/**
 * 对应 {@link MelonsEnableRepository } 的服务接口
 * @param <T> 实体
 * @param <R> 实体仓库
 *
 * @author krun
 * @date 2018/05/29
 */
public interface MelonsEnableService<T extends EnableField, R extends MelonsEnableRepository<T>>
		extends MelonsUUIDService<T, R> {}
