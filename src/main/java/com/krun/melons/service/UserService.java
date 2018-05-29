/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UserService.java
 * Date:    18-5-29 下午4:33
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.service.MelonsEnableService;
import com.krun.melons.entity.UserEntity;
import com.krun.melons.repository.UserRepository;

/**
 * 用户服务接口
 *
 * @author krun
 * @date 2018/05/29
 */
public interface UserService extends MelonsEnableService<UserEntity, UserRepository> {}
