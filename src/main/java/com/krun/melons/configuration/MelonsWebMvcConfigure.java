/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsWebMvcConfigure.java
 * Date:    18-5-29 上午10:46
 * Author: krun
 */

package com.krun.melons.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * 配置 MVC 相关信息
 *
 * @author krun
 * @date 2018/05/29
 */
@Configuration
public class MelonsWebMvcConfigure implements WebMvcRegistrations {

	/**
	 * 解决 Fetch_Lazy 模式下的 entity_manager_session 丢失问题
	 * @return {@link OpenEntityManagerInViewFilter}
	 */
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
		return new OpenEntityManagerInViewFilter();
	}

}
