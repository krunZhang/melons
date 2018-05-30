/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LoadSystemProperties.java
 * Date:    18-5-30 上午9:24
 * Author: krun
 */

package com.krun.melons.initialization;

import com.krun.melons.configuration.properties.MelonsSystemProperties;
import com.krun.melons.entity.PropertyEntity;
import com.krun.melons.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化一些全局属性，比如管理员初始帐号密码
 */
@Component
@AllArgsConstructor
public class LoadSystemProperties implements CommandLineRunner {

	private MelonsSystemProperties systemProperties;

	private PropertyService propertyService;

	@Override
	public void run (String... args) throws Exception {
		PropertyEntity managerUsername = new PropertyEntity();
		managerUsername.setName(systemProperties.getManagerUsernameKey());
		managerUsername.setDescription("管理员账户对应的用户名");
		managerUsername.setKey(systemProperties.getManagerUsernameKey());
		managerUsername.setValue(systemProperties.getManagerUsernameValue());

		PropertyEntity managerPassword = new PropertyEntity();
		managerPassword.setName(systemProperties.getManagerPasswordKey());
		managerPassword.setDescription("管理员账户的初始密码");
		managerPassword.setKey(systemProperties.getManagerPasswordKey());
		managerPassword.setValue(systemProperties.getManagerPasswordValue());

		propertyService.findByKeyOrDefault(systemProperties.getManagerUsernameKey(), managerUsername);
		propertyService.findByKeyOrDefault(systemProperties.getManagerPasswordKey(), managerPassword);
	}
}
