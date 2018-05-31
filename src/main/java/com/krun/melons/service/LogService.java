/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LogService.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.service.MelonsUUIDService;
import com.krun.melons.entity.LogEntity;
import com.krun.melons.repository.LogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * 日志服务接口
 *
 * @author krun
 * @date 2018/05/30
 */

public interface LogService extends MelonsUUIDService<LogEntity, LogRepository> {

	default Page<LogEntity> list(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	default Page<LogEntity> list(Specification<LogEntity> specification, Pageable pageable) {
		return getRepository().findAll(specification, pageable);
	}

	@Override
	default LogEntity save (LogEntity entity) {
		if ("/logs".equals(entity.getPermission()
		                         .getUri())) {
			return entity;
		}
		return getRepository().save(entity);
	}
}
