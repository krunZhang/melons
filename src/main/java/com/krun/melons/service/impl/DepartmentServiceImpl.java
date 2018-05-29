/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      DepartmentServiceImpl.java
 * Date:    18-5-29 下午4:33
 * Author: krun
 */

package com.krun.melons.service.impl;

import com.krun.melons.repository.DepartmentRepository;
import com.krun.melons.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * 部门服务实现类
 *
 * @author krun
 * @date 2018/05/29
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	@Getter
	private DepartmentRepository repository;

}
