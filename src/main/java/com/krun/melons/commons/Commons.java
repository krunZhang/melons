/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      Commons.java
 * Date:    18-5-30 上午9:45
 * Author: krun
 */

package com.krun.melons.commons;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * 一些暂未分类的帮助方法都先放在这里
 * @author krun
 * @date 2018/05/29
 */
public class Commons {

	public static final Predicate<List> IS_LIST_EMPTY = list -> list == null || list.size() == 0;

	@SuppressWarnings("unchecked")
	public static <K, V> HashMap<K, V> ofMap(Object ...objects) {
		if (objects.length == 0) {
			return new HashMap<>(0);
		}
		HashMap<K, V> map = new HashMap<>(objects.length / 2);
		for (int i = 0; i < objects.length; i += 2) {
			map.put((K) objects[i], (V) objects[i + 1]);
		}
		return map;
	}

}
