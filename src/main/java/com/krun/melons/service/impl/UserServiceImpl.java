/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UserServiceImpl.java
 * Date:    18-5-30 上午9:45
 * Author: krun
 */

package com.krun.melons.service.impl;

import com.krun.melons.configuration.properties.MelonsJwtProperties;
import com.krun.melons.configuration.properties.MelonsSystemProperties;
import com.krun.melons.core.jwt.JwtToken;
import com.krun.melons.entity.UserEntity;
import com.krun.melons.repository.UserRepository;
import com.krun.melons.service.PropertyService;
import com.krun.melons.service.UserService;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

/**
 * 用户服务实现类
 *
 * @author krun
 * @date 2018/05/29
 */
@Service
public class UserServiceImpl implements UserService {

	@Getter
	private UserRepository repository;

	private MelonsSystemProperties systemProperties;
	private PropertyService propertyService;
	private PasswordEncoder encoder = createDelegatingPasswordEncoder();
	private MelonsJwtProperties jwtProperties;
	public UserServiceImpl (UserRepository repository, MelonsSystemProperties systemProperties,
	                        PropertyService propertyService, MelonsJwtProperties jwtProperties) {
		this.repository = repository;
		this.systemProperties = systemProperties;
		this.propertyService = propertyService;
		this.jwtProperties = jwtProperties;
	}

	@Override
	public boolean isManager (UserEntity userEntity) {
		return propertyService.findByKey(systemProperties.getManagerUsernameKey())
				.orElseThrow(() -> new RuntimeException(""))
				.getValue().equals(userEntity.getUsername());
	}
	@Override
	public String login (String username, String password) {
		UserEntity user = findByUsername(username).orElseGet(() -> {
			UserEntity manager = new UserEntity();
			manager.setUsername(propertyService.getByKeyOrThrow(systemProperties.getManagerUsernameKey()));
			manager.setPassword(propertyService.getByKeyOrThrow(systemProperties.getManagerPasswordKey()));
			return manager;
		});
		if (!user.getEnable()) {
			throw new RuntimeException("该用户已被禁用!");
		}
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("密码错误!");
		}
		user.setToken(JwtToken.empty(jwtProperties).generateToken(username));
		return save(user).getToken();
	}
}
