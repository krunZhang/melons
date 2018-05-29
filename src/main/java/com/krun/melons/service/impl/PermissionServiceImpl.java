/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionServiceImpl.java
 * Date:    18-5-29 下午4:17
 * Author: krun
 */

package com.krun.melons.service.impl;

import com.krun.melons.repository.PermissionRepository;
import com.krun.melons.service.PermissionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * 权限服务实现类
 *
 * @author krun
 * @date 2018/05/29
 */
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

	@Getter
	private PermissionRepository repository;
}


