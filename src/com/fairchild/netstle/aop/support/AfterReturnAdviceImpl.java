/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop.support;

import java.lang.reflect.Method;

import com.fairchild.netstle.aop.AfterReturnAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public class AfterReturnAdviceImpl implements AfterReturnAdvice {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.netstle.netstleaop.IAfterReturnAdvice#afterReturn(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object, java.lang.Object)
	 */
	public Object afterReturn(Object proxy, Method method, Object[] args, Object target, Object returnValue) throws Throwable {
		System.out.println("AfterReturnAdvice performs.");
		return returnValue;
	}

}
