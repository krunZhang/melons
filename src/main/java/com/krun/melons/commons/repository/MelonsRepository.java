/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MelonsRepository.java
 * Date:    18-5-29 上午11:29
 * Author: krun
 */

package com.krun.melons.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 结合 {@link JpaRepository} 和 {@link JpaSpecificationExecutor} 作为基础仓库。
 * @param <T> 实体
 * @param <ID> 实体主键类型
 *
 * @author krun
 * @date 2018/05/29
 */
@NoRepositoryBean
public interface MelonsRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {}
