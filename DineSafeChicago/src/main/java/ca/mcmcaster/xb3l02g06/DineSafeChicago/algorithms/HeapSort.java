package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;

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
	public static void sortHeap(Comparable[] x, int n) {
		for (int k = n / 2; k >= 1; k--)
			sink(x, k, n);

		while (n > 1) {
			exch(x, 1, n--);
			sink(x, 1, n);
		}
	}

	private static void sink(Comparable[] x, int k, int n) {
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

	private static boolean less(Comparable[] x, int i, int j) {
		return x[i - 1].compareTo(x[j - 1]) < 0;
	}

	private static void exch(Comparable[] x, int i, int j) {
		Comparable t = x[i - 1];
		x[i - 1] = x[j - 1];
		x[j - 1] = t;
	}

	public static void main(String[] args) {
//		// TEST HERE
//		// After implmentation, create an array of restaurants to test
//		Restaurant[] res = new Restaurant[9];
//		res[0] = new Restaurant("1", 204, 2, 3, 404, "Rome Road");
//		res[1] = new Restaurant("2", 204, 2, 3, 404, "Rome Road");
//		res[2] = new Restaurant("3", 204, 2, 3, 404, "Rome Road");
//		res[3] = new Restaurant("4", 204, 2, 3, 404, "Rome Road");
//		res[4] = new Restaurant("5", 204, 2, 3, 404, "Rome Road");
//		res[5] = new Restaurant("6", 204, 2, 3, 404, "Rome Road");
//		res[6] = new Restaurant("7", 204, 2, 3, 404, "Rome Road");
//		res[7] = new Restaurant("8", 204, 2, 3, 404, "Rome Road");
//		res[8] = new Restaurant("9", 204, 2, 3, 404, "Rome Road");
//		res[0].setOverallScore(80);
//		res[1].setOverallScore(40);
//		res[2].setOverallScore(70);
//		res[3].setOverallScore(20);
//		res[4].setOverallScore(50);
//		res[5].setOverallScore(90);
//		res[6].setOverallScore(30);
//		res[7].setOverallScore(60);
//		res[8].setOverallScore(10);
//
//		sortHeap(res, 9);
//		for (int i = 0; i < 9; i++) {
//			System.out.println(res[i]);
//		}

	}

}
