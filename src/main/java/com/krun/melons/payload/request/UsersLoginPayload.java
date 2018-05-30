/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      UsersLoginPayload.java
 * Date:    18-5-30 上午9:45
 * Author: krun
 */

package com.krun.melons.payload.request;

import lombok.Data;

/**
 * 登录负载
 *
 * @author krun
 * @date 2018/05/30
 */
@Data
public class UsersLoginPayload {

	private String username;
	private String password;

}
