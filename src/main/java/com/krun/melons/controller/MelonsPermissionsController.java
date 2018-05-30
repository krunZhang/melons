/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsPermissionsController.java
 * Date:    18-5-30 下午2:38
 * Author: krun
 */

package com.krun.melons.controller;

import com.krun.melons.commons.exception.EntityNotFoundException;
import com.krun.melons.commons.payload.ResponseData;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.payload.request.PermissionsModifiedPayload;
import com.krun.melons.payload.request.PermissionsSpecificationPayload;
import com.krun.melons.service.PermissionService;
import com.krun.spring.extend.mapping.RestDomainMapping;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器
 *
 * @author krun
 * @date 2018/05/29
 */
@RestDomainMapping
@AllArgsConstructor
public class MelonsPermissionsController extends MelonsController {

	private PermissionService permissionService;

	@GetMapping("")
	public ResponseData<Page<PermissionEntity>> list(@PageableDefault Pageable pageable,
	                                                 PermissionsSpecificationPayload payload) {
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getPageSize());
		Page<PermissionEntity> page;
		if (payload == null) {
			page = permissionService.findAll(pageable);
		} else {
			page = payload.getEnable() ?
			       permissionService.findAllEnable(pageable) :
			       permissionService.findAllNotEnable(pageable);
		}
		return ResponseData.<Page<PermissionEntity>>ok().data(page).build();
	}

	@DeleteMapping("/{id}")
	public ResponseData<PermissionEntity> delete(@PathVariable("id") PermissionEntity permission) {
		permissionService.delete(permission);
		return ResponseData.<PermissionEntity>ok().build();
	}

	@PutMapping("/{id}")
	public ResponseData<PermissionEntity> update(@PathVariable("id") PermissionEntity permission,
	                                             @RequestBody PermissionsModifiedPayload payload) {
		if (permission == null) {
			throw new EntityNotFoundException("权限不存在!");
		}
		return ResponseData.<PermissionEntity>ok()
				.data(permissionService.save(payload.merge(permission)))
				.build();
	}
}
