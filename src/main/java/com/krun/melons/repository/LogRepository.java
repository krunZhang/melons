/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LogRepository.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsUUIDRepository;
import com.krun.melons.entity.LogEntity;
import org.springframework.stereotype.Repository;

/**
 * 日志仓库
 *
 * @author krun
 * @date 2018/05/30
 */
@Repository
public interface LogRepository extends MelonsUUIDRepository<LogEntity> {}
