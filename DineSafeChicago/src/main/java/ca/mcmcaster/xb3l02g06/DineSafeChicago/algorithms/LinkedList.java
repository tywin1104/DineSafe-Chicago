package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import java.util.*;

public class LinkedList<AnyType> implements Iterable<AnyType> {
	/**
	 * State variables
	 */
	private Node<AnyType> head;
	private int size;

	/**
	 * Constructs an empty list
	 */
	public LinkedList() {
		head = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public void addFirst(AnyType item) {
		head = new Node<AnyType>(item, head);
		size++;
	}

	public void addLast(AnyType item) {
		if (head == null)
			addFirst(item);
		else {
			Node<AnyType> tmp = head;
			while (tmp.next != null)
				tmp = tmp.next;

			tmp.next = new Node<AnyType>(item, null);
			size++;
		}

	}

	public AnyType get(int pos) {
		if (head == null)
			throw new IndexOutOfBoundsException();

		Node<AnyType> tmp = head;
		for (int k = 0; k < pos; k++)
			tmp = tmp.next;

		if (tmp == null)
			throw new IndexOutOfBoundsException();

		return tmp.data;
	}

	/**
	 * Returns a string representation
	 *
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Object x : this)
			result.append(x + " ");

		return result.toString();
	}

	/*******************************************************
	 *
	 * The Node class
	 *
	 ********************************************************/
	private static class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> next;

		public Node(AnyType data, Node<AnyType> next) {
			this.data = data;
			this.next = next;
		}
	}

	/*******************************************************
	 *
	 * The Iterator class
	 *
	 ********************************************************/

	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> nextNode;

		public LinkedListIterator() {
			nextNode = head;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public AnyType next() {
			if (!hasNext())
				throw new NoSuchElementException();
			AnyType res = nextNode.data;
			nextNode = nextNode.next;
			return res;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
