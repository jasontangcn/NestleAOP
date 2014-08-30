/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop;

import java.lang.reflect.Method;

/**
 * @author TomHornson@hotmail.com
 */
public interface BeforeAdvice extends Advice {
	public void before(Object proxy, Method method, Object[] args, Object target) throws Throwable;
}
