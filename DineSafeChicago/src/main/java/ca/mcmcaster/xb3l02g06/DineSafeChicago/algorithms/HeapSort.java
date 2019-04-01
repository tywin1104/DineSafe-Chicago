package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import java.util.ArrayList;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;

/**
 * This class implements heap sort algorithm.
 * 
 * @author Kexin Liu
 * 
 */

public class HeapSort {

	/**
	 * heap sort using Comparable
	 * 
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortHeap(ArrayList<Restaurant> x, int n) {
		for (int k = n / 2; k >= 1; k--)
			sink(x, k, n);

		while (n > 1) {
			exch(x, 1, n--);
			sink(x, 1, n);
		}
	}

	private static void sink(ArrayList<Restaurant> x, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(x, j, j + 1))
				j++;
			if (!less(x, k, j))
				break;
			exch(x, k, j);
			k = j;
		}
	}

	private static boolean less(ArrayList<Restaurant> x, int i, int j) {
		return (x.get(i-1)).compareTo(x.get(j - 1)) < 0;
	}

	private static void exch(ArrayList<Restaurant> x, int i, int j) {
		Restaurant t = x.get(i-1);
		
		x.set(i - 1, x.get(j - 1));
		x.set(j - 1, t);
	}

	public static void main(String[] args) {
//		// TEST HERE
//		// After implmentation, create an array of restaurants to test
		ArrayList<Restaurant> res = new ArrayList();
		Restaurant r1 = new Restaurant(new RestaurantIdentity("123", "123"), 20, 2.0, 3.0,100);
		Restaurant r2 = new Restaurant(new RestaurantIdentity("456", "456"), 20, 2.0, 3.0,100);
		Restaurant r3 = new Restaurant(new RestaurantIdentity("789", "789"), 20, 2.0, 3.0,100);
		r1.setOverallScore(80);
		r2.setOverallScore(70);
		r3.setOverallScore(90);
		res.add(r1);
		res.add(r2);
		res.add(r3);
		sortHeap(res, 3);
		for (int i = 0; i < 3; i++) {
			System.out.println(res.get(i));
		}

	}

}
