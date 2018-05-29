/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      DepartmentService.java
 * Date:    18-5-29 下午4:33
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.service.MelonsItemDescriptionService;
import com.krun.melons.entity.DepartmentEntity;
import com.krun.melons.repository.DepartmentRepository;

/**
 * 部门服务接口
 *
 * @author krun
 * @date 2018/05/29
 */
public interface DepartmentService extends MelonsItemDescriptionService<DepartmentEntity, DepartmentRepository> {}
