/*
 * Created on Jan 4, 2005
 */
package com.fruits.netstle.aop.framework;

import java.lang.reflect.Method;

import com.fruits.netstle.aop.AroundAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public abstract class AbstractAroundAdvice implements AroundAdvice {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netstle.netstleaop.IAroundAdvice#aroud(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
	 */
	public Object process(Object proxy, Method method, Object[] args, Object target) throws Throwable {
		AroundAdviceNode aan;
		aan = AroundAdviceNode.getLastNode();
		if (null != aan) {
			return aan.getAroundAdvice().process(proxy, method, args, target);
		} else {
			return method.invoke(target, args);
		}
	}
}
