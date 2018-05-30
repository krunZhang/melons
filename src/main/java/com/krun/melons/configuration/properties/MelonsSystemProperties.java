/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsSystemProperties.java
 * Date:    18-5-30 上午9:22
 * Author: krun
 */

package com.krun.melons.configuration.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Melons 本身的一些全局设置
 *
 * @author krun
 * @date 2018/05/30
 */
@Component
@Getter
public class MelonsSystemProperties {

	@Value("${melons.config.manager.username.key:MANAGER_USERNAME}")
	private String managerUsernameKey;
	@Value("${melons.config.manager.password.key:MANAGER_PASSWORD}")
	private String managerPasswordKey;

	@Value("${melons.config.manager.username.value:Melons}")
	private String managerUsernameValue;
	@Value("${melons.config.manager.password.value:Melons}")
	private String managerPasswordValue;

}
