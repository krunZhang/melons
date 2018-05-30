/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UserService.java
 * Date:    18-5-30 上午9:24
 * Author: krun
 */

package com.krun.melons.service;

import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.service.MelonsEnableService;
import com.krun.melons.entity.UserEntity;
import com.krun.melons.repository.UserRepository;

import java.util.Optional;

/**
 * 用户服务接口
 *
 * @author krun
 * @date 2018/05/29
 */
public interface UserService extends MelonsEnableService<UserEntity, UserRepository> {

	default Optional<UserEntity> findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	default Optional<UserEntity> findByUsernameAndEnable(String username, Boolean enable) {
		return getRepository().findByUsernameAndEnable(username, enable);
	}

	default UserEntity findByUsernameOrThrow(String username) {
		return findByUsername(username).orElseThrow(() -> new EntityNotFoundException("找不到用户: name: " + username));
	}

	default UserEntity findByUsernameAndEnableOrThrow(String username, Boolean enable) {
		return findByUsernameAndEnable(username, enable)
				.orElseThrow(() -> new EntityNotFoundException("找不到用户: name: " + username + ", enable: " + enable));
	}

	boolean isManager(UserEntity userEntity);

	String login(String username, String password);

}
