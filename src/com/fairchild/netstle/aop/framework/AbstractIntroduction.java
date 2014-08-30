/*
 * Created on Jan 4, 2005
 */
package com.fairchild.netstle.aop.framework;

import java.lang.reflect.Method;

import com.fairchild.netstle.aop.Introduction;

/**
 * @author TomHornson@hotmail.com
 */
public abstract class AbstractIntroduction implements Introduction {
	public void invoke(Object proxy, Method method, Object[] args, Object target) throws Throwable {
		boolean signal = false;
		Class clazzDeclared = method.getDeclaringClass();
		// System.out.println(clazzDeclared);

		Class[] intfs = this.getClass().getInterfaces();
		for (int i = 0; i < intfs.length; i++) {
			if (intfs[i].getName().equals(clazzDeclared.getName())) {
				// System.out.println(intfs[i]);
				signal = true;
				method.invoke(this, args);
				return;
			}
		}

		System.out.println("Mixin begins.");
		if (!signal)
			mixin(proxy, method, args, target);
	}
}
