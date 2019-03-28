package ca.mcmcaster.xb3l02g06.DineSafeChicago.algorithms;

import java.util.ArrayList;

import ca.mcmcaster.xb3l02g06.DineSafeChicago.restaurant.Restaurant;

public class HashRes {

	private ArrayList<LinkedList<Restaurant>> hashTable;

	// Constructor
	public HashRes() {
		this.hashTable = new ArrayList<LinkedList<Restaurant>>();
		for (int i = 0; i < 100; i++) {
			this.hashTable.add(new LinkedList<Restaurant>());
		}
	}

	public void convertToHash(ArrayList<Restaurant> resList) {
		for (int i = 0; i < resList.size(); i++) {
			insertRes(resList.get(i));
		}
	}

	public void insertRes(Restaurant res) {
		// Compute the index using Hash function
		int index = hashFunc(res.getZip());
		// Insert the element in the linked list at the particular index
		this.hashTable.get(index).addLast(res);
	}

	public int hashFunc(int zip) {
		return zip % 100;
	}

	public ArrayList<Restaurant> searchZip(int zip) {
		// Create an ArrayList for restaurants having the same zipcode
		ArrayList<Restaurant> resSameZip = new ArrayList<Restaurant>();
		// Compute the index by using the hash function
		int index = hashFunc(zip);
		// Search the linked list at the specific index
		for (int i = 0; i < hashTable.get(index).getSize(); i++) {
			if (hashTable.get(index).get(i).getZip() == zip) {
				resSameZip.add(hashTable.get(index).get(i));
			}
		}
		return resSameZip;
	}

	public static void main(String[] args) {

		/*
		 * Inspection inspection1 = new
		 * Inspection(ResultT.CONDITIONALPASS,"1 PERSON MAINTAIN","01/02/2018");
		 * Inspection inspection2 = new
		 * Inspection(ResultT.INVALID,"41 WIPING CLOTAIN","03/05/2019"); Inspection
		 * inspection3 = new Inspection(ResultT.PASS,"","12/05/2018"); Inspection
		 * inspection4 = new Inspection(ResultT.FAIL,"23 aaaaaa","03/05/2017");
		 * 
		 * Restaurant res1 = new Restaurant ("aaa", 1114, 1141, 114.1, 411.1);
		 * Restaurant res2 = new Restaurant("aab", 1121, 1131, 121.1, 121.1); Restaurant
		 * res3 = new Restaurant("aas", 1131, 1141, 131.1, 131.1); Restaurant res4 = new
		 * Restaurant("aaf", 1111, 1121, 111.1, 121.1);
		 * 
		 * res1.addInspection(inspection1); res1.addInspection(inspection2);
		 * res1.addInspection(inspection3); res1.addInspection(inspection4);
		 * 
		 * res2.addInspection(inspection2); res2.addInspection(inspection1);
		 * 
		 * res3.addInspection(inspection4);
		 * 
		 * res4.addInspection(inspection3); res4.addInspection(inspection4);
		 * res4.addInspection(inspection1);
		 * 
		 * ArrayList<Restaurant> resList = new ArrayList<Restaurant>();
		 * resList.add(res1); resList.add(res2); resList.add(res3); resList.add(res4);
		 * HashRes hashTable = new HashRes(); hashTable.convertToHash(resList);
		 * System.out.println(hashTable.searchZip(1141));
		 */
	}

}
