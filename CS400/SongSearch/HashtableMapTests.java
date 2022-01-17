// --== CS400 Project One File Header ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: Blue
// Group: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

import java.util.NoSuchElementException;

/**
 * This class thoroughly tests the methods found in the HashtableMap.java class
 * 
 * @author Matt Stilling
 */
public class HashtableMapTests {

	/**
	 * Tests the constructor methods and size()
	 * 
	 * @return true if all tests passed, false otherwise
	 */
	public static boolean test1() {
		try {
			// Create first hash table
			HashtableMap<Integer, String> hashTable1 = new HashtableMap<>();
			if (hashTable1.size() != 0) {
				System.out.println("Error in test1(): size should be 0, but was instead " 
						+ hashTable1.size());
				return false;
			}
			
			// Create second hash table (different generic types)
			HashtableMap<String, Boolean> hashTable2 = new HashtableMap<>();
			if (hashTable2.size() != 0) {
				System.out.println("Error in test1(): size should be 0, but was instead " 
						+ hashTable2.size());
				return false;
			}
			
			// Create third hash table (using second constructor)
			HashtableMap<Integer, String> hashTable3 = new HashtableMap<>(15);
			if (hashTable3.size() != 0) {
				System.out.println("Error in test1(): size should be 0, but was instead " 
						+ hashTable3.size());
				return false;
			}

		} catch (Exception e) { // An unexpected exception was thrown
			e.printStackTrace();
			return false;
		}
		return true; // All tests passed
	}

	/**
	 * Tests the put() and get() methods
	 * 
	 * @return true if all tests passed, false otherwise
	 */
	public static boolean test2() {
		try {
			HashtableMap<Integer, String> hashTable = new HashtableMap<>(10);
			
			// Test adding first pair
			if (!hashTable.put(10, "Hello")) {
				System.out.println("Error in test2(): adding pair with key 10 should work, but "
						+ "put() returned false");
				return false;
			}
			if (hashTable.size() != 1) {
				System.out.println("Error in test2(): size should be 1, but was instead " 
						+ hashTable.size());
				return false;
			}
			if (!hashTable.get(10).equals("Hello")) {
				System.out.println("Error in test2(): get() should return Hello, instead returned "
						+ hashTable.get(10));
				return false;
			}
			
			// Test adding second pair (no collision)
			if (!hashTable.put(7, "Goodbye")) {
				System.out.println("Error in test2(): adding pair with key 7 should work, but "
						+ "put() returned false");
				return false;
			}
			if (hashTable.size() != 2) {
				System.out.println("Error in test2(): size should be 2, but was instead " 
						+ hashTable.size());
				return false;
			}
			if (!hashTable.get(7).equals("Goodbye")) {
				System.out.println("Error in test2(): get() should return Goodbye, instead returned "
						+ hashTable.get(7));
				return false;
			}
			
			// Test adding third pair (with collision)
			if (!hashTable.put(20, "Crash")) {
				System.out.println("Error in test2(): adding pair with key 20 should work, but "
						+ "put() returned false");
				return false;
			}
			if (hashTable.size() != 3) {
				System.out.println("Error in test2(): size should be 3, but was instead " 
						+ hashTable.size());
				return false;
			}
			if (!hashTable.get(20).equals("Crash")) {
				System.out.println("Error in test2(): get() should return Crash, instead returned "
						+ hashTable.get(20));
				return false;
			}
			
			// Test adding pair with null key value
			if (hashTable.put(null, null)) {
				System.out.println("Error in test2(): adding pair with null key value should "
						+ "return false");
				return false;
			}
			
			// Test adding pair with duplicate key value
			if (hashTable.put(10, "Duplicate")) {
				System.out.println("Error in test2(): adding pair with duplicate key value should "
						+ "return false");
				return false;
			}
			
		} catch (Exception e) { // An unexpected exception was thrown
			e.printStackTrace();
			return false;
		}
		return true; // All tests passed
	}

	/**
	 * Test the containsKey() method
	 * 
	 * @return true if all tests passed, false otherwise
	 */
	public static boolean test3() {
		try {
			HashtableMap<Integer, String> hashTable = new HashtableMap<>(10);
			hashTable.put(10, "Hello");
			hashTable.put(7, "Goodbye");
			hashTable.put(20, "Crash");
			
			// Test for key that is in the hash table
			if (!hashTable.containsKey(10)) {
				System.out.println("Error in test3(): hashtable contains the key 10, but "
						+ "containsKey(10) returned false");
				return false;
			}
			
			// Second test for key that is in the hash table
			if (!hashTable.containsKey(7)) {
				System.out.println("Error in test3(): hashtable contains the key 7, but "
						+ "containsKey(7) returned false");
				return false;
			}
			
			// Third test for key that is in the hash table (collision)
			if (!hashTable.containsKey(20)) {
				System.out.println("Error in test3(): hashtable contains the key 20, but "
						+ "containsKey(20) returned false");
				return false;
			}
			
			// Test for key that is NOT in the hash table
			if (hashTable.containsKey(5)) {
				System.out.println("Error in test3(): hashtable does not contain the key 5, but "
						+ "containsKey(5) returned true");
				return false;
			}
			
		} catch (Exception e) { // An unexpected exception was thrown
			e.printStackTrace();
			return false;
		}
		return true; // All tests passed
	}

	/**
	 * Tests the get() method
	 * 
	 * @return true if all tests passed, false otherwise
	 */
	public static boolean test4() {
		try {
			HashtableMap<Integer, String> hashTable = new HashtableMap<>(10);
			hashTable.put(10, "Hello");
			hashTable.put(7, "Goodbye");
			hashTable.put(20, "Crash");
			
			// Test for key that is in the hash table
			if (!hashTable.get(10).equals("Hello")) {
				System.out.println("Error in test4(): get(10) should return Hello, but instead "
						+ "returned " + hashTable.get(10));
				return false;
			}
			
			// Second test for key that is in the hash table
			if (!hashTable.get(7).equals("Goodbye")) {
				System.out.println("Error in test4(): get(7) should return Goodbye, but instead "
						+ "returned " + hashTable.get(7));
				return false;
			}
			
			// Third test for key that is in the hash table (collision)
			if (!hashTable.get(20).equals("Crash")) {
				System.out.println("Error in test4(): get(20) should return Crash, but instead "
						+ "returned " + hashTable.get(20));
				return false;
			}
			
			// Test for key that is NOT in the hash table
			try {
				hashTable.get(5); // Should throw NoSuchElementException
				System.out.println("Error in test4(): NoSuchElementException should have been "
						+ "thrown, but it was not");
				return false;
			} catch (NoSuchElementException e) {
				// Do nothing, NoSuchElementException should be caught
			}
			
		} catch (Exception e) { // An unexpected exception was thrown
			e.printStackTrace();
			return false;
		}
		return true; // All tests passed

	}

	/**
	 * Tests the remove() and clear() methods
	 * 
	 * @return true if all tests passed, false otherwise
	 */
	public static boolean test5() {
		try {
			HashtableMap<Integer, String> hashTable = new HashtableMap<>(10);
			hashTable.put(10, "Hello");
			hashTable.put(7, "Goodbye");
			hashTable.put(20, "Crash");
			
			// Test removing a valid pair from the hash table
			if (!hashTable.remove(10).equals("Hello")) {
				System.out.println("Error in test5(): remove(10) expected Hello returned");
				return false;
			}
			if (hashTable.size() != 2) {
				System.out.println("Error in test5(): size after removing one pair should be 2, "
						+ "instead was " + hashTable.size());
				return false;
			}
			
			// Test removing an invalid pair from the hash table
			if (!(hashTable.remove(10) == null)) {
				System.out.println("Error in test5(): remove(10) (after first removal) expected "
						+ "null returned");
				return false;
			}
			if (hashTable.size() != 2) {
				System.out.println("Error in test5(): size after removing one pair should be 2, "
						+ "instead was " + hashTable.size());
				return false;
			}
			
			// Test removing a second valid pair from the hash table
			if (!hashTable.remove(20).equals("Crash")) {
				System.out.println("Error in test5(): remove(20) expected Crash returned");
				return false;
			}
			if (hashTable.size() != 1) {
				System.out.println("Error in test5(): size after removing two pairs should be 1, "
						+ "instead was " + hashTable.size());
				return false;
			}
			
			// Test clear() method
			hashTable.put(3, "Good");
			hashTable.put(4, "Bad"); // Size now 3
			hashTable.clear();
			if (hashTable.size() != 0) {
				System.out.println("Error in test5(): size after clearing should be 0, instead was "
						+ hashTable.size());
				return false;
			}
			
		} catch (Exception e) { // An unexpected exception was thrown
			e.printStackTrace();
			return false;
		}
		return true; // All tests passed
	}

	/**
	 * Main method only used to run test methods
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("test1: " + test1());
		System.out.println("test2: " + test2());
		System.out.println("test3: " + test3());
		System.out.println("test4: " + test4());
		System.out.println("test5: " + test5());
	}

}
