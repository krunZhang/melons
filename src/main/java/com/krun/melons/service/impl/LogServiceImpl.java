/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LogServiceImpl.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.service.impl;

import com.krun.melons.repository.LogRepository;
import com.krun.melons.service.LogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现
 *
 * @author krun
 * @date 2018/05/30
 */
@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {

	@Getter
	private LogRepository repository;

}
