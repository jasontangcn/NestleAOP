/*
 * Created on Jan 3, 2005
 */
package com.fruits.netstle.aop.support;

import java.lang.reflect.Method;

import com.fruits.netstle.aop.framework.AbstractAroundAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public class AroundAdviceImpl extends AbstractAroundAdvice {
	public Object around(Object proxy, Method method, Object[] args, Object target) throws Throwable {
		Object returnValue = null;

		System.out.println("AroundAdvice starts.");
		returnValue = process(proxy, method, args, target);
		System.out.println("AroundAdvice ended.");

		return returnValue;
	}

}
