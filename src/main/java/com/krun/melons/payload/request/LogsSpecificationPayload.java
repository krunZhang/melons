/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LogsSpecificationPayload.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.payload.request;

import com.krun.melons.entity.LogEntity;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.entity.UserEntity;
import com.krun.melons.payload.Payload;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志搜索条件负载
 *
 * @author krun
 * @date 2018/05/30
 */
@Data
public class LogsSpecificationPayload extends Payload implements Specification<LogEntity> {

	private String  uri;
	private String  method;
	private Boolean enable;
	private Boolean intercept;
	//	private Date date;
	//	private Date after;
	//	private Date before;
	private String  username;
	private String  token;

	private String ip;
	private String url;

	/**
	 * 解析查询条件并构造动态查询
	 *
	 * @return
	 */
	private void mergeUserSpecification (List<Predicate> predicates, Root<LogEntity> root,
	                                     CriteriaBuilder criteriaBuilder) {
		Join<LogEntity, UserEntity> userJoin = root.join("user", JoinType.LEFT);
		if (getUsername() != null) {
			predicates.add(criteriaBuilder.equal(userJoin.get("username")
			                                             .as(String.class), getUsername()));
		}
		if (getToken() != null) {
			predicates.add(criteriaBuilder.equal(userJoin.get("token")
			                                             .as(String.class), getToken()));
		}
	}

	private void mergePermissionSpecificatrion (List<Predicate> predicates, Root<LogEntity> root,
	                                            CriteriaBuilder criteriaBuilder) {
		Join<LogEntity, PermissionEntity> permissionJoin = root.join("permission", JoinType.LEFT);
		if (getUri() != null) {
			predicates.add(criteriaBuilder.equal(permissionJoin.get("uri")
			                                                   .as(String.class), getUri()));
		}
		if (getMethod() != null) {
			predicates.add(criteriaBuilder.equal(permissionJoin.get("method")
			                                                   .as(RequestMethod.class),
			                                     RequestMethod.valueOf(getMethod())));
		}
		if (getIntercept() != null) {
			predicates.add(criteriaBuilder.equal(permissionJoin.get("intercept")
			                                                   .as(Boolean.class), getIntercept()));
		}
		if (getEnable() != null) {
			predicates.add(criteriaBuilder.equal(permissionJoin.get("enable")
			                                                   .as(Boolean.class), getIntercept()));
		}
	}
	@Override
	public Predicate toPredicate (Root<LogEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		ArrayList<Predicate> predicates = new ArrayList<>();

		mergeUserSpecification(predicates, root, criteriaBuilder);
		mergePermissionSpecificatrion(predicates, root, criteriaBuilder);

		if (getIp() != null) {
			predicates.add(criteriaBuilder.equal(root.get("ip")
			                                         .as(String.class), getIp()));
		}
		if (getUrl() != null) {
			predicates.add(criteriaBuilder.equal(root.get("url")
			                                         .as(String.class), getUrl()));
		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}
