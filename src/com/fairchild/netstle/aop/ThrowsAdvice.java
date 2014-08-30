/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop;

import java.lang.reflect.Method;

/**
 * @author TomHornson@hotmail.com
 */
public interface ThrowsAdvice extends Advice {
	public Object afterThrows(Object proxy, Method method, Object[] args, Object target, Throwable t) throws Throwable;
}
