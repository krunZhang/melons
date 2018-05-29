/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      EnableField.java
 * Date:    18-5-29 上午10:19
 * Author: krun
 */

package com.krun.melons.commons.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 任何继承此类的实体都将获得字段:
 * <code>enable:Boolean</code> 类型的字段，默认值为 true。
 * 此外，此类还继承了 {@link UUIDField}，因此也会获得一个 <code>id:String(UUID)</code> 的主键。
 * @author krun
 * @date 2018/05/29
 */
@MappedSuperclass
@Getter
@Setter
public class EnableField extends UUIDField {

	@Column(name = "enable", columnDefinition = "bit(1) default 1")
	private Boolean enable;

}
