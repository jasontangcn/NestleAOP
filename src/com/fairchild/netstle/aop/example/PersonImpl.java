package com.fairchild.netstle.aop.example;

import com.fairchild.netstle.aop.Advice;
import com.fairchild.netstle.aop.AfterReturnAdvice;
import com.fairchild.netstle.aop.AroundAdvice;
import com.fairchild.netstle.aop.Introduction;
import com.fairchild.netstle.aop.ThrowsAdvice;
import com.fairchild.netstle.aop.framework.ProxyFactory;
import com.fairchild.netstle.aop.support.AfterReturnAdviceImpl;
import com.fairchild.netstle.aop.support.AroundAdviceImpl;
import com.fairchild.netstle.aop.support.BeforeAdviceImpl;
import com.fairchild.netstle.aop.support.Lockable;
import com.fairchild.netstle.aop.support.LockableMixin;
import com.fairchild.netstle.aop.support.ThrowsAdviceImpl;

/**
 * @author TomHornson@hotmail.com
 */
public class PersonImpl implements IPerson, ISay, IEat {
	private int age = 0;

	public PersonImpl() {
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void say(String words) {
		System.out.println(words);
	}

	public void eat(String food) {
		System.out.println(food);
	}

	public static void main(String[] args) throws Throwable {
		PersonImpl person = new PersonImpl();
		person.setAge(100);

		ProxyFactory factory = new ProxyFactory(person);

		/*
		 * Interfaces supposed to be intercepted.
		 */
		factory.addProxyInterfaces(new Class[] { IPerson.class, ISay.class, IEat.class });

		factory.addAdvices(new Advice[] { new BeforeAdviceImpl() });
		factory.addAdvices(new AroundAdvice[] { new AroundAdviceImpl() });
		factory.addAdvices(new AfterReturnAdvice[] { new AfterReturnAdviceImpl() });
		factory.addAdvices(new ThrowsAdvice[] { new ThrowsAdviceImpl() });

		/*
		 * Adding Mixin.
		 * Mixin should extends AbstractIntroduction and implements mixin interfaces.
		 * e.g. LockableMixin extends AbstractIntroduction and implments Lockable, for getting lock functionality.
		 * Methods in IIntrocution are the key of the mixin.
		 */
		factory.addMixins(new Introduction[] { new LockableMixin(new Class[] { Lockable.class }) });

		Object proxy = factory.getProxy();

		try {
			((IPerson) proxy).getAge();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to getAge.");
		}

		try {
			((ISay) proxy).say("ISay.");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to say.");
		}
		try {
			((IEat) proxy).eat("Apple.");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to eat.");
		}
		try {
			((Lockable) proxy).lock();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to eat.");
		}
		try {
			((IEat) proxy).eat("Apple.");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to eat.");
		}
		try {
			((Lockable) proxy).unlock();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to eat.");
		}
		try {
			((IEat) proxy).eat("Apple.");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("You do not have permession to eat.");
		}
	}
}
