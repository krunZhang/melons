/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsLogsController.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.controller;

import com.krun.melons.commons.payload.ResponseData;
import com.krun.melons.entity.LogEntity;
import com.krun.melons.payload.request.LogsSpecificationPayload;
import com.krun.melons.service.LogService;
import com.krun.spring.extend.mapping.RestDomainMapping;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 日志控制器
 *
 * @author krun
 * @date 2018/05/30
 */
@RestDomainMapping
@AllArgsConstructor
public class MelonsLogsController extends MelonsController {

	private LogService logService;

	@GetMapping("")
	public ResponseData<Page<LogEntity>> list(@PageableDefault Pageable pageable, LogsSpecificationPayload payload) {
		Page<LogEntity> page;
		if (payload.allNull()) {
			page = logService.list(pageable);
		} else {
			page = logService.list(payload, pageable);
		}
		return ResponseData.<Page<LogEntity>>ok()
				.data(page)
				.build();
	}
}
