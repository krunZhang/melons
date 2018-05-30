/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PropertyServiceImpl.java
 * Date:    18-5-30 上午9:24
 * Author: krun
 */

package com.krun.melons.service.impl;

import com.krun.melons.repository.PropertyRepository;
import com.krun.melons.service.PropertyService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * 全局属性服务
 *
 * @author krun
 * @date 2018/05/30
 */
@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

	@Getter
	private PropertyRepository repository;

}
