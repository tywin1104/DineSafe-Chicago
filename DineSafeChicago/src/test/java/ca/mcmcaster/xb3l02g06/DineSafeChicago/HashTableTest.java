/**
 * 
 */
package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.RestaurantIdentity;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;
import ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable;

/**
 * @author Administrator
 *
 */
public class HashTableTest {
    Restaurant r1;
	Restaurant r3;
	Restaurant r2;
	HashTable ht;
	ArrayList<Restaurant> al;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		r1 = new Restaurant (new RestaurantIdentity("aaa","aab"), 1141, 1141, 3.0, 110);
		r2 = new Restaurant (new RestaurantIdentity("bbb","aab"), 1142, 1141, 3.0, 110);
		r3 = new Restaurant (new RestaurantIdentity("bbc","aab"), 1142, 1142, 3.0, 110);
		ht =new HashTable(10);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable#loadRestaurants(java.util.ArrayList)}.
	 */
	@Test
	public void testLoadRestaurants() {
		al= new ArrayList<Restaurant>();
		al.add(r1);
		al.add(r2);
		al.add(r3);
		ht.loadRestaurants(al);
		assertEquals(ht.st[1].get(0),r1);
		assertEquals(ht.st[2].get(0),r2);
		assertEquals(ht.st[2].get(1),r3);
		
	}

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable#put(ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant)}.
	 */
	@Test
	public void testPut() {
        ht.put(r1);
        ht.put(r2);
        ht.put(r3);
		assertEquals(r1,ht.st[1].get(0));
		assertEquals(ht.st[2].get(0),r2);
		assertEquals(ht.st[2].get(1),r3);
		
	}
	

	/**
	 * Test method for {@link ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms.HashTable#getRestaurants(int)}.
	 */
	@Test
	public void testGetRestaurants() {
		ht.put(r1);
        ht.put(r2);
        ht.put(r3);
        assertEquals(r1,ht.getRestaurants(1141).get(0));
        assertEquals(r2,ht.getRestaurants(1142).get(0));
        assertEquals(r3,ht.getRestaurants(1142).get(1));
		
	}

}
