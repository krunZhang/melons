/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsUUIDService.java
 * Date:    18-5-29 下午3:12
 * Author: krun
 */

package com.krun.melons.commons.service;

import com.krun.melons.commons.entity.UUIDField;
import com.krun.melons.commons.repository.MelonsUUIDRepository;

/**
 * 对应 {@link MelonsUUIDRepository } 的服务接口
 * @param <T> 实体
 * @param <R> 实体仓库
 *
 * @author krun
 * @date 2018/05/29
 */
public interface MelonsUUIDService<T extends UUIDField, R extends MelonsUUIDRepository<T>> extends MelonsService<T, String, R> {}
