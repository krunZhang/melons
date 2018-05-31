/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      PermissionsSpecificationPayload.java
 * Date:    18-5-31 上午9:22
 * Author: krun
 */

package com.krun.melons.payload.request;

import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.payload.Payload;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

/**
 * 权限条件
 *
 * @author krun
 * @date 2018/05/30
 */
@Data
public class PermissionsSpecificationPayload extends Payload<PermissionEntity> implements Specification<PermissionEntity> {

	private String name;
	private String method;
	private String uri;
	private Boolean enable;
	private Boolean intercept;

	@Override
	public Predicate toPredicate (Root<PermissionEntity> root, CriteriaQuery<?> query,
	                              CriteriaBuilder criteriaBuilder) {
		ArrayList<Predicate> predicates = this.mapToPrdicates(root, criteriaBuilder);
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}
