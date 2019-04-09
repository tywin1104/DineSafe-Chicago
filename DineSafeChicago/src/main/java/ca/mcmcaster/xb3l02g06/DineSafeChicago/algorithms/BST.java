package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;

/**
 * Generates a key-value paired using Binary Search Tree.
 * 
 * @author Kexin Liu
 *
 * @param <Key> Key to search for Value
 * @param <Value> Value that corresponds to Key
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int size;

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	/**
	 * Initializes symbol table.
	 */
	public BST() {
	}

	/**
	 * Determines whether the BST is empty.
	 * 
	 * @return true if the size of BST is empty, false if it is not
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Determines the size of BST
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	/**
	 * Determines whether the BST contains the key.
	 *
	 * @param key the key
	 * @return true if contains, false if it does not
	 * @throws IllegalArgumentException if the key is null
	 */
	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("The BST does not contain the key.");
		return get(key) != null;
	}

	/**
	 * Gets the value associated with the given key.
	 *
	 * @param key the key
	 * @return the value that paired with the given key
	 * @throws IllegalArgumentException if the key is null
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (key == null)
			throw new IllegalArgumentException("Null key recerived.");
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	/**
	 * Puts the ky-value pair into the BST.
	 *
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if the key is null
	 */
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("Null key received.");
		if (val == null)
			throw new IllegalArgumentException("Null value received.");
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
}
