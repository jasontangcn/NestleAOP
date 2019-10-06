/*
 * Created on Jan 3, 2005
 */
package com.fruits.netstle.aop.support;

import java.lang.reflect.Method;

import com.fruits.netstle.aop.BeforeAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public class BeforeAdviceImpl implements BeforeAdvice {
	/*
	 * If Throwable thrown here,the invocation on the target object will be intercepted.
	 */
	public void before(Object proxy, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BeforeAdvice performs.");
		if ("say".equals(method.getName())) {
			throw new Throwable("say is intecepted in BeforeAdviceImpl.");
		}
	}
}
