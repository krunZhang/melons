/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsMappingNameResolver.java
 * Date:    18-5-29 下午5:57
 * Author: krun
 */

package com.krun.melons.configuration;

import com.krun.melons.configuration.properties.MelonsDomainMappingProperties;
import com.krun.spring.extend.mapping.resolver.impl.AbstractMappingNameResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 解析类名到路由路径
 *
 * @author krun
 * @date 2018/05/29
 */
@Component
@AllArgsConstructor
public class MelonsMappingNameResolver extends AbstractMappingNameResolver {

	private MelonsDomainMappingProperties properties;

	@Override
	protected String getSuffix () {
		return properties.getSuffix();
	}
	@Override
	protected String getPrefix () {
		return properties.getPrefix();
	}
}
