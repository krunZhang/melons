/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      LogEntity.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.entity;

import com.krun.melons.commons.entity.UUIDField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * 日志实体
 *
 * @author krun
 * @date 2018/05/30
 */
@Entity(name = "log")
@Getter
@Setter
public class LogEntity extends UUIDField {

	@OneToOne(targetEntity = PermissionEntity.class)
	private PermissionEntity permission;

	@Column(updatable = false)
	private String url;

	@OneToOne(targetEntity = UserEntity.class)
	private UserEntity user;

	@Column(nullable = false, updatable = false)
	private String ip;

	@Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP")
	private Timestamp time;

	@Column(updatable = false)
	private Boolean access = true;

	private String notes;

}
