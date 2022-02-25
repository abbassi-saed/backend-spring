package com.ecommerce.controllers;

import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {
	
	public static String auth = SecurityContextHolder.getContext().getAuthentication().getName();

}
