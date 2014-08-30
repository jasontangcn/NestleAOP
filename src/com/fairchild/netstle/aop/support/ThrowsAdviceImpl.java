/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop.support;

import java.lang.reflect.Method;

import com.fairchild.netstle.aop.ThrowsAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public class ThrowsAdviceImpl implements ThrowsAdvice {
	public Object afterThrows(Object proxy, Method method, Object[] args, Object target, Throwable t) throws Throwable {
		System.out.println("ThrowsAdvice performs.");
		System.out.println(t);
		return null;
	}
}
