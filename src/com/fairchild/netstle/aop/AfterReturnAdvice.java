/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop;

import java.lang.reflect.Method;

/**
 * @author TomHornson@hotmail.com
 */
public interface AfterReturnAdvice extends Advice {
	public Object afterReturn(Object proxy, Method method, Object[] args, Object target, Object returnValue) throws Throwable;
}