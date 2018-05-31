/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MooseGlobalIntercept.java
 * Date:    18-5-31 上午9:10
 * Author: krun
 */

package com.krun.melons.core.intercept;

import com.krun.melons.commons.exception.ForbiddenException;
import com.krun.melons.commons.exception.TokenExpiredException;
import com.krun.melons.commons.payload.ResponseData;
import com.krun.melons.configuration.properties.MelonsJwtProperties;
import com.krun.melons.core.jwt.JwtToken;
import com.krun.melons.entity.LogEntity;
import com.krun.melons.entity.PermissionEntity;
import com.krun.melons.entity.UserEntity;
import com.krun.melons.service.LogService;
import com.krun.melons.service.PermissionService;
import com.krun.melons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 全局拦截器
 *
 * @author krun
 * @date 2018/05/29
 */
@ControllerAdvice
public class MooseGlobalIntercept {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	private PermissionService   permissionService;
	private UserService         userService;
	private MelonsJwtProperties jwtConfiguration;
	private LogService          logService;

	private Map<RequestMappingInfo, HandlerMethod> handlerMethodMap;

	public MooseGlobalIntercept (PermissionService permissionService, UserService userService,
	                             MelonsJwtProperties jwtConfiguration, LogService logService) {
		this.permissionService = permissionService;
		this.userService = userService;
		this.jwtConfiguration = jwtConfiguration;
		this.logService = logService;
	}


	@PostConstruct
	public void afterConstructor () {
		this.handlerMethodMap = handlerMapping.getHandlerMethods();
		scanHandlerMethods();
	}

	/**
	 * 扫描路由映射，注册权限单元
	 */
	private void scanHandlerMethods () {
		Set<String> patterns;
		Set<RequestMethod> methods;
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
			RequestMappingInfo info    = entry.getKey();
			HandlerMethod      handler = entry.getValue();
			patterns = info.getPatternsCondition().getPatterns();
			methods = info.getMethodsCondition().getMethods();

			for (String  pattern : patterns) {
				for (RequestMethod method : methods) {
					registerPermission(pattern, method, handler);
				}
			}
		}
	}

	private void registerPermission(String pattern, RequestMethod method, HandlerMethod handler) {
		if (permissionService.findByUriAndMethod(pattern, method)
		                     .isPresent()) {
			return;
		}
		PermissionEntity permission = new PermissionEntity();
		permission.setName(handler.getBeanType()
		                          .getSimpleName() + "_" + handler.getMethod()
		                                                          .getName());
		permission.setUri(pattern);
		permission.setMethod(method);
		permission.setIntercept(false);
		permission.setEnable(true);
		permissionService.save(permission);
		System.out.println(String.format("Register permission: { %s }",
		                                 permission.toString()));
	}

	@ResponseStatus (HttpStatus.OK)
	@ResponseBody
	@ExceptionHandler (Exception.class)
	public ResponseData<String> signException (Exception ex) {
		//		if (debug) {
		ex.printStackTrace();
		//		}
		return ResponseData.<String>badRequest().message(ex.getMessage())
		                                        .build();
	}

	@ModelAttribute
	public void preHandleRequest (HttpServletRequest request) {

		/* 找出匹配的pattern*/
		String    pattern = match(request);
		String    method  = request.getMethod();



		/* 检查是否需要拦截 */
		PermissionEntity permission = permissionService.findByUriAndMethodOrThrow(pattern, RequestMethod.valueOf(method));


		if (! permission.getEnable()) {
			throw new RuntimeException("此接口暂时关闭!");
		}
		if (! permission.getIntercept()) {
			return;
		}

		/* 解析 token */
		JwtToken jwtToken = JwtToken.fromRequest(request, jwtConfiguration);

		if (jwtToken.isTokenExpired()) {
			throw new TokenExpiredException();
		}

		String username = jwtToken.getUsername();
		UserEntity user = userService.findByUsernameOrThrow(username);

		LogEntity log     = new LogEntity();
		log.setUrl(request.getRequestURL().toString());
		log.setTime(Timestamp.from(Calendar.getInstance().toInstant()));
		log.setIp(request.getRemoteAddr());
		log.setPermission(permission);
		log.setUser(user);
		logService.save(log);

		if (userService.isManager(user)) {
			log.setNotes("管理员权限");
			log.setAccess(true);
			logService.save(log);
			return;
		}
		if (! user.hasPermission(permission)) {
			log.setNotes("无权限访问");
			log.setAccess(false);
			logService.save(log);
			throw new ForbiddenException(String.format("无权限访问 {url: '%s', method: %s}", request.getRequestURI(), method));
		}

		log.setAccess(true);
		logService.save(log);

	}

	private String match (HttpServletRequest request) {
		List<String> patterns;
		for (RequestMappingInfo info : handlerMethodMap.keySet()) {
			patterns = info.getPatternsCondition()
			               .getMatchingPatterns(request.getRequestURI());
			if (patterns.size() != 0) {
				return patterns.get(0);
			}

		}
		throw new RuntimeException("找不到匹配 " + request.getRequestURI() + " 的路由信息!");
	}

}
