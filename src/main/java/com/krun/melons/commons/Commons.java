/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      Commons.java
 * Date:    18-5-29 上午11:04
 * Author: krun
 */

package com.krun.melons.commons;

import java.util.List;
import java.util.function.Predicate;

/**
 * 一些暂未分类的帮助方法都先放在这里
 * @author krun
 * @date 2018/05/29
 */
public class Commons {

	public static final Predicate<List> IS_LIST_EMPTY = list -> list == null || list.size() == 0;

}
