/**
 * 
 */
package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;

/**
 * @author Administrator
 *
 */
public class BSTTest {
	BST<Integer, Restaurant> st;
	Restaurant r1;
	Restaurant r2;
	
	Restaurant r3;
	Restaurant r4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		r1 = new Restaurant(new RestaurantIdentity("123", "123"), 20, 2.0, 3.0, 100);
		r2 = new Restaurant(new RestaurantIdentity("456", "456"), 20, 2.0, 3.0, 110);
		r3 = new Restaurant(new RestaurantIdentity("789", "789"), 20, 2.0, 3.0, 120);
		r4 = new Restaurant(new RestaurantIdentity("zyl", "zyl"), 20, 2.0, 3.0, 130);
		st =new BST<Integer, Restaurant>();
		r1.setOverallScore(80);
		r2.setOverallScore(70);
		r3.setOverallScore(70);
		r4.setOverallScore(20);

		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertEquals(true,st.isEmpty());
		st.put(100,r1);
		assertEquals(false,st.isEmpty());
		st.put(110, r2);
		assertEquals(false,st.isEmpty());
		
		
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST#size()}.
	 */
	@Test
	public void testSize() {
		st.put(100, r1);
		assertEquals(1,st.size());
		st.put(110,r2);
		assertEquals(2,st.size());
		st.put(120,r3);
		assertEquals(3,st.size());
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContains() {
		st.put(100, r1);
		
		st.put(110,r2);
		
		st.put(120,r3);
		assertEquals(true,st.contains(100));
		assertEquals(true,st.contains(110));
		assertEquals(true,st.contains(120));
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST#get(java.lang.Comparable)}.
	 */
	@Test
	public void testGet() {
        st.put(100, r1);
		
		st.put(110,r2);
		
		st.put(120,r3);
		assertEquals(r1,st.get(100));
		assertEquals(r2,st.get(110));
		assertEquals(r3,st.get(120));
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.BST#put(java.lang.Comparable, java.lang.Object)}.
	 */
	@Test
	public void testPut() {
        st.put(100, r1);
		
		st.put(110,r2);
		
		st.put(120,r3);
		assertEquals(r1,st.get(100));
		assertEquals(r2,st.get(110));
		assertEquals(r3,st.get(120));
		
	}

}
