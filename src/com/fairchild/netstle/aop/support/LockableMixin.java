/*
 * Created on Jan 4, 2005
 */
package com.fairchild.netstle.aop.support;

import java.lang.reflect.Method;
import com.fairchild.netstle.aop.framework.AbstractIntroduction;

/**
 * @author TomHornson@hotmail.com
 */
public class LockableMixin extends AbstractIntroduction implements Lockable {
	private boolean locked = false;
	private Class[] intfs;

	public LockableMixin(Class[] intfs) {
		this.intfs = intfs;
	}

	public void lock() {
		locked = true;
	}

	public void unlock() {
		locked = false;
	}

	public boolean getLock() {
		return locked;
	}

	public void setIntfs(Class[] intfs) {
		this.intfs = intfs;
	}

	public Class[] getIntfs() {
		return intfs;
	}

	public void mixin(Object proxy, Method method, Object[] args, Object target) throws Throwable {
		if (getLock() && (0 == method.getName().indexOf("eat"))) {
			throw new Throwable("You are locked.");
		}
	}
}
