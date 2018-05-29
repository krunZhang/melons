/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsDomainMappingProperties.java
 * Date:    18-5-29 下午5:57
 * Author: krun
 */

package com.krun.melons.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MappingName 相关配置项
 *
 * @author krun
 * @date 2018/05/29
 */
@Component
@Data
public class MelonsDomainMappingProperties {

	@Value("${melons.domain-mapping.prefix:Melons}")
	private String prefix;

	@Value("${melons.domain-mapping.suffix:Controller}")
	private String suffix;

}
