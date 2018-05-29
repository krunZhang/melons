/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UserRepository.java
 * Date:    18-5-29 上午11:56
 * Author: krun
 */

package com.krun.melons.repository;

import com.krun.melons.commons.repository.MelonsEnableRepository;
import com.krun.melons.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户仓库
 *
 * @author krun
 * @date 2018/05/29
 */
@Repository
public interface UserRepository extends MelonsEnableRepository<UserEntity> {

	/**
	 * 查找用户，无论是何种启用状态
	 * @param username 用户名
	 * @return 返回用 {@link Optional} 包裹的用户实体
	 */
	Optional<UserEntity> findByUsername(String username);

	/**
	 * 查找用户，同时要求处于特定启用状态
	 * @param username 用户名
	 * @param enable 用户启用状态
	 * @return 返回用 {@link Optional} 包裹的用户实体
	 */
	Optional<UserEntity> findByUsernameAndEnable(String username, Boolean enable);

	/**
	 * 检查指定用户是否存在，无论是何种启用状态
	 * @param username 用户名
	 * @return 检查结果
	 */
	boolean existsByUsername(String username);

	/**
	 * 检查指定用户是否存在，同时要求处于特定启用状态
	 * @param username 用户名
	 * @param enable 启用状态
	 * @return 检查结果
	 */
	boolean existsByUsernameAndEnable(String username, Boolean enable);
}
