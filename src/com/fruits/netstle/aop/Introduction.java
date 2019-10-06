/*
 * Created on Jan 4, 2005
 */
package com.fruits.netstle.aop;

import java.lang.reflect.Method;

/**
 * @author TomHornson@hotmail.com
 *
 */
public interface Introduction {
	public void invoke(Object proxy, Method method, Object[] args, Object target) throws Throwable;

	public void mixin(Object proxy, Method method, Object[] args, Object target) throws Throwable;
}
