/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsWebMvcConfigure.java
 * Date:    18-5-29 下午5:57
 * Author: krun
 */

package com.krun.melons.configuration;

import com.krun.spring.extend.mapping.handler.DomainMappingHandler;
import com.krun.spring.extend.mapping.resolver.MappingNameResolver;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 配置 MVC 相关信息
 *
 * @author krun
 * @date 2018/05/29
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@AllArgsConstructor
public class MelonsWebMvcConfigure implements WebMvcRegistrations {

	private MappingNameResolver mappingNameResolver;

	@Override
	@Bean (name = "requestMappingHandlerMapping")
	@Primary
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping () {
		return new DomainMappingHandler(mappingNameResolver);
	}

	/**
	 * 解决 Fetch_Lazy 模式下的 entity_manager_session 丢失问题
	 * @return {@link OpenEntityManagerInViewFilter}
	 */
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
		return new OpenEntityManagerInViewFilter();
	}

}
