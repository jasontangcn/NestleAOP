/*
 * Created on Jan 4, 2005
 */
package com.fruits.netstle.aop.support;

/**
 * @author TomHornson@hotmail.com
 */
public interface Lockable {
	public void lock();
	public void unlock();
}
