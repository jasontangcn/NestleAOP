/*
 * Created on Jan 3, 2005
 */
package com.fruits.netstle.aop;

import java.lang.reflect.Method;

/**
 * @author TomHornson@hotmail.com
 */
public interface AroundAdvice extends Advice {
	public Object around(Object proxy, Method method, Object[] args, Object target) throws Throwable;

	public Object process(Object proxy, Method method, Object[] args, Object target) throws Throwable;
}
