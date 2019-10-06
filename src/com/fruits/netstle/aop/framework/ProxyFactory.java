/*
 * Created on Jan 2, 2005
 */
package com.fruits.netstle.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import com.fruits.netstle.aop.Advice;
import com.fruits.netstle.aop.AfterReturnAdvice;
import com.fruits.netstle.aop.AroundAdvice;
import com.fruits.netstle.aop.BeforeAdvice;
import com.fruits.netstle.aop.Introduction;
import com.fruits.netstle.aop.ThrowsAdvice;

/**
 * @author TomHornson@hotmail.com
 */

public class ProxyFactory implements InvocationHandler {
	private Object target;

	private ArrayList proxyInterfaces = new ArrayList();
	private ArrayList beforeAdvices = new ArrayList();
	private ArrayList afterReturnAdvices = new ArrayList();
	private ThrowsAdvice throwsAdvice = null;
	private ArrayList introductions = new ArrayList();

	public ProxyFactory(Object target) throws Throwable {
		this.target = target;
	}

	public void addProxyInterfaces(Class[] proxyIntfs) throws Throwable {
		// Should under JDK5.0
		/*
		 * Collections.addAll(this.proxyInterfaces,(Object[])proxyInterfaces);
		 */
		if ((null != proxyIntfs) && (proxyIntfs.length > 0)) {
			for (int i = 0; i < proxyIntfs.length; i++) {
				this.proxyInterfaces.add(proxyIntfs[i]);
			}
		}
	}

	public void addAdvices(Advice[] advices) throws Throwable {
		if (null != advices) {
			for (int i = 0; i < advices.length; i++) {
				Advice advice = advices[i];

				if (advice instanceof BeforeAdvice) {
					beforeAdvices.add(advice);
				}

				if (advice instanceof AfterReturnAdvice) {
					afterReturnAdvices.add(advice);
				}

				if (advice instanceof ThrowsAdvice) {
					throwsAdvice = (ThrowsAdvice) advice;
				}

				if (advice instanceof AroundAdvice) {
					AroundAdviceNode.addAroundAdvice((AroundAdvice) advice);
				}
			}
		}
	}

	public void addMixins(Introduction[] intros) {
		// Should under JDK5.0
		/*
		 * Collections.addAll(this.introductions,(Object[])introductions);
		 */
		if ((null != intros) && (intros.length > 0)) {
			for (int i = 0; i < intros.length; i++) {
				this.introductions.add(intros[i]);
			}
		}
	}

	public Object getProxy() throws Throwable {
		int capabilityProxyIntf = proxyInterfaces.size();

		/*
		 * In fact we could use getIntfs() of IntroductionImpl.
		 * But I can not request much from users.
		 */
		ArrayList introItfs = new ArrayList();
		for (int i = 0; i < introductions.size(); i++) {
			Class[] intfs = introductions.get(i).getClass().getInterfaces();
			for (int j = 0; j < intfs.length; j++) {
				if (!intfs[j].getName().equals("Introduction"))
					introItfs.add(intfs[j]);
			}
		}

		int total = capabilityProxyIntf + introItfs.size();
		Class[] tmp = new Class[total];
		for (int i = 0; i < capabilityProxyIntf; i++) {
			tmp[i] = (Class) proxyInterfaces.get(i);
		}
		for (int i = 0; i < introItfs.size(); i++) {
			tmp[capabilityProxyIntf + i] = (Class) introItfs.get(i);
		}
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), tmp, this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String returnType = null;
		Object returnValue = null;
		int capability = 0;

		try {
			capability = introductions.size();
			/*
			 * Notice: sequence issue with mixin's
			 * Always you would bind mixin interfaces to specific business methods instead of all of the methods.
			 * If one business method has multiple mixin's, there is a priority issue.
			 * In the mixin stack, the mixin in the front makes the decision to conitue or stop.
			 */
			for (int i = 0; i < capability; i++) {
				((Introduction) introductions.get(i)).invoke(proxy, method, args, target);
			}

			capability = beforeAdvices.size();
			for (int i = 0; i < capability; i++) {
				((BeforeAdvice) beforeAdvices.get(i)).before(proxy, method, args, target);
			}

			returnValue = AroundAdviceNode.getLastNode().getAroundAdvice().around(proxy, method, args, target);

			capability = afterReturnAdvices.size();
			for (int i = 0; i < capability; i++) {
				returnValue = ((AfterReturnAdvice) afterReturnAdvices.get(i)).afterReturn(proxy, method, args, target, returnValue);
			}
			return returnValue;
		} catch (Throwable e) {
			if (null != throwsAdvice) {
				return throwsAdvice.afterThrows(proxy, method, args, target, e);
			} else {
				throw e;
			}
		} finally {
			System.out.println("An invocation has been performed!\n");
		}
	}
}
