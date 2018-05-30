/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsJwtProperties.java
 * Date:    18-5-30 上午9:22
 * Author: krun
 */

package com.krun.melons.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Jwt 相关配置项
 *
 * @author krun
 * @date 2018/05/30
 */
@Component
@Data
public class MelonsJwtProperties {

	@Value("${melons.config.jwt.header:Melons}")
	private String header;
	@Value("${melons.config.jwt.prefix:Melons-}")
	private String prefix;
	@Value("${melons.config.jwt.secret:1525678639}")
	private String secret;
	@Value("${melons.config.jwt.expiration:604800}")
	private Long expiration;

}
