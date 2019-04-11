package unittest;

/**
 * 
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HeapSort;

/**
 * @author Administrator
 *
 */
public class HeapSortTest {
	Restaurant r1;
	Restaurant r3;
	Restaurant r2;

	ArrayList<Restaurant> res;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		r1 = new Restaurant(new RestaurantIdentity("aaa", "aab"), 1141, 1141, 3.0, 110);
		r2 = new Restaurant(new RestaurantIdentity("bbb", "aab"), 1142, 1141, 3.0, 110);
		r3 = new Restaurant(new RestaurantIdentity("bbc", "aab"), 1142, 1142, 3.0, 110);
		res = new ArrayList<Restaurant>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HeapSort#sortHeap(ArrayList<Restaurant>,
	 * int)}.
	 */
	@Test
	public void testSortHeap() {
		r1.setOverallScore(80);
		r2.setOverallScore(70);
		r3.setOverallScore(90);
		res.add(r1);
		res.add(r2);
		res.add(r3);
		HeapSort.sortHeap(res, 3);
		assertEquals(res.get(0), r3);
		assertEquals(res.get(1), r1);
		assertEquals(res.get(2), r2);
	}
}
