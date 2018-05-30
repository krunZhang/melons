/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsUsersController.java
 * Date:    18-5-30 上午9:45
 * Author: krun
 */

package com.krun.melons.controller;

import com.krun.melons.commons.Commons;
import com.krun.melons.commons.payload.ResponseData;
import com.krun.melons.payload.request.UsersLoginPayload;
import com.krun.melons.service.UserService;
import com.krun.spring.extend.mapping.RestDomainMapping;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

/**
 * 用户控制器
 *
 * @author krun
 * @date 2018/05/30
 */
@RestDomainMapping
@AllArgsConstructor
public class MelonsUsersController extends MelonsController {

	private UserService userService;

	@PostMapping("/login")
	public ResponseData<HashMap<String, String>> login(@RequestBody UsersLoginPayload payload) {
		return ResponseData.<HashMap<String, String>>ok()
				.data(Commons.ofMap("token", userService.login(payload.getUsername(), payload.getPassword())))
				.build();
	}
}
