package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import java.util.*;

/**
 * a linked list data structure for hash table
 * @author Shanghong Shen
 * @version April 1, 2019
 * @param <AnyType> - parameterized type to be contained in the linked list
 */

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

	/**
	 * Get the size of the linked list
	 * @return size - an integer indicating the number of elements contained in the linked list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * add the input item to the beginning of the linked list
	 * @param item
	 */
	public void addFirst(AnyType item) {
		head = new Node<AnyType>(item, head);
		size++;
	}

	/**
	 * add the input item to the end of the linked list
	 * @param item
	 */
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

	/**
	 * get the item at the input index 
	 * @param pos - the index indicating the position of the desired item in the linked list
	 * @return data - the data containing at the input position in the linked list
	 */
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
	 * Returns a string representation of the linked list
	 *
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Object x : this)
			result.append(x + " ");

		return result.toString();
	}

/**
 * 
 * @author Shanghong Shen
 * @version April 1, 2019
 * @param <AnyType> - A parameterized type to be contained in the data part of the node
 * 
 */
	private static class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> next;

		/**
		 * Constructor of the Node class
		 * @param data - data to be contained in the node
		 * @param next - a reference to the next node in the linked list
		 */
		public Node(AnyType data, Node<AnyType> next) {
			this.data = data;
			this.next = next;
		}
	}


	/**
	 * The iterator class
	 */
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> nextNode;
		
		/**
		 * Constructor fot the iterator of the linked list
		 */
		public LinkedListIterator() {
			nextNode = head;
		}

		/**
		 * Check if the current node is at the end of the linked list
		 * @return boolean -  true if current node is not the end of the linked list, otherwise false
		 */
		public boolean hasNext() {
			return nextNode != null;
		}

		/**
		 * return the next element
		 * @return res - next node
		 * @throws java.lang.Exception
		 */
		public AnyType next() {
			if (!hasNext())
				throw new NoSuchElementException();
			AnyType res = nextNode.data;
			nextNode = nextNode.next;
			return res;
		}

		/**
		 * clean the current linked list
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
