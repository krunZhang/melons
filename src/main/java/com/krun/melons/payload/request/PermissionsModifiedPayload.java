/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionsModifiedPayload.java
 * Date:    18-5-30 下午2:38
 * Author: krun
 */

package com.krun.melons.payload.request;

import com.krun.melons.commons.Commons;
import com.krun.melons.entity.PermissionEntity;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 修改权限时的负载
 *
 * @author krun
 * @date 2018/05/30
 */
@Data
public class PermissionsModifiedPayload {

	private String name;
	private String description;
	private String uri;
	private String method;
	private Boolean enable;
	private Boolean intercept;

	public PermissionEntity merge(PermissionEntity permission) {
		if (!Commons.IS_STRING_EMPTY.test(name)) {
			permission.setName(name);
		}
		if (!Commons.IS_STRING_EMPTY.test(description)) {
			permission.setDescription(description);
		}
		if (!Commons.IS_STRING_EMPTY.test(uri)) {
			permission.setUri(uri);
		}
		if (!Commons.IS_STRING_EMPTY.test(method)) {
			permission.setMethod(RequestMethod.valueOf(method));
		}
		if (enable != null) {
			permission.setEnable(enable);
		}
		if (intercept != null) {
			permission.setIntercept(intercept);
		}
		return permission;
	}

}
