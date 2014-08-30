/*
 * Created on Jan 3, 2005
 */
package com.fairchild.netstle.aop.framework;

import com.fairchild.netstle.aop.AroundAdvice;

/**
 * @author TomHornson@hotmail.com
 */
public class AroundAdviceNode {
	private AroundAdvice aroundAdvice;
	private AroundAdviceNode nextNode;

	public AroundAdviceNode() {
	}

	public AroundAdviceNode(AroundAdvice aroundAdvice) {
		this.aroundAdvice = aroundAdvice;
	}

	public void setAroundAdvice(AroundAdvice currentNode) {
		aroundAdvice = currentNode;
	}

	public AroundAdvice getAroundAdvice() {
		return aroundAdvice;
	}

	public void setNextNode(AroundAdviceNode nextNode) {
		this.nextNode = nextNode;
	}

	public AroundAdviceNode getNextNode() {
		return nextNode;
	}

	private static AroundAdviceNode head = null;
	private static AroundAdviceNode lastNode = null;

	public static AroundAdviceNode getLastNode() {
		if (lastNode == null) {
			lastNode = head;
			return lastNode;
		}
		lastNode = lastNode.getNextNode();
		return lastNode;
	}

	public static void addAroundAdvice(AroundAdvice aroundAdvice) {
		if (null == head) {
			head = new AroundAdviceNode(aroundAdvice);
		} else {
			AroundAdviceNode tmp = head;
			while (null != tmp.getNextNode()) {
				tmp = tmp.getNextNode();
			}
			tmp.setNextNode(new AroundAdviceNode(aroundAdvice));
		}
	}
}
