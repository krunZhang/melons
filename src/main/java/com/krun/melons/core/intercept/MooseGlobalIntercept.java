/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      MooseGlobalIntercept.java
 * Date:    18-5-29 下午6:10
 * Author: krun
 */

package com.krun.melons.core.intercept;

import com.krun.melons.commons.payload.ResponseData;
import com.krun.melons.entity.PermissionEntity;
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
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MooseGlobalIntercept {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	private PermissionService permissionService;
	private UserService       userService;
//	private JwtConfiguration  jwtConfiguration;

	private Map<RequestMappingInfo, HandlerMethod> handlerMethodMap;

	public MooseGlobalIntercept (PermissionService permissionService, UserService userService) {
		this.permissionService = permissionService;
		this.userService = userService;
//		this.jwtConfiguration = jwtConfiguration;
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
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
			RequestMappingInfo info    = entry.getKey();
			HandlerMethod      handler = entry.getValue();

			info.getPatternsCondition()
			    .getPatterns()
			    .forEach(pattern -> info.getMethodsCondition()
			                            .getMethods()
			                            .forEach(method -> {
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
			                            }));
		}
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
		String pattern = match(request);
		String method  = request.getMethod();

		/* 检查是否需要拦截 */
		PermissionEntity permission = permissionService.findByUriAndMethodOrThrow(pattern, RequestMethod.valueOf(method));

		if (! permission.getEnable()) {
			throw new RuntimeException("此接口暂时关闭!");
		}
		if (! permission.getIntercept()) {
			/* 不拦截 */
			System.out.println(
					String.format("此接口不拦截: {pattern: '%s', uri: '%s', method: '%s'}", pattern, request.getRequestURI(),
					              method));
			return;
		}

//		/* 解析 token */
//		JwtToken jwtToken = JwtToken.fromRequest(request, jwtConfiguration);
//
//		if (jwtToken.isTokenExpired()) {
//			throw new RuntimeException("Token 已过期!");
//		}
//
//		String username = jwtToken.getUsername();
//
//		User user = userService.findByUsername(username);
//		if (! user.hasPermission(permission)) {
//			throw new RuntimeException(String.format("无权限访问 {url: '%s', method: %s}", request.getRequestURI(), method));
//		}

//		System.out.println(
//				String.format("Access: { user: '%s', pattern: '%s', uri: '%s', method: '%s', permission: '%s'}",
//				              username, pattern, request.getRequestURI(), method, permission.getName()));

		System.out.println(
				String.format("Access: { pattern: '%s', uri: '%s', method: '%s', permission: '%s'}",
						             pattern, request.getRequestURI(), method, permission.getName()));

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
