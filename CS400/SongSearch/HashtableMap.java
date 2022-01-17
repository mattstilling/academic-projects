// --== CS400 Project One File Header ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: Blue
// Group: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements a hash table data structure that stores key-value pairs of generic types.
 * 
 * @author Matt Stilling
 * @param <KeyType> the generic key type
 * @param <ValueType> the generic value type
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	
	private LinkedList<KeyValuePair<KeyType, ValueType>>[] hashTable; // The hash table array
	
	/**
	 * Constructs a new hash table with the specified capacity
	 * 
	 * @param capacity the initial capacity of the hash table
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		hashTable = (LinkedList<KeyValuePair<KeyType, ValueType>>[]) new LinkedList[capacity];
	}
	
	/**
	 * Constructs a new hash table with a default capacity of 20
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		hashTable = (LinkedList<KeyValuePair<KeyType, ValueType>>[]) new LinkedList[20];
	}
	
	/**
	 * Inserts a new key-value pair into the hash table.
	 * 
	 * @param key the key in the key-value pair to be inserted
	 * @param value the value in the key-value pair to be inserted
	 * @return true if the key-value pair successfully inserted, false otherwise
	 */
	public boolean put(KeyType key, ValueType value) {
		if (key == null) return false;
		int hashIndex = Math.abs(key.hashCode())%hashTable.length; // Hash function
		KeyValuePair<KeyType, ValueType> newPair = new KeyValuePair<>(key, value);
		if (hashTable[hashIndex] == null) { // hashIndex empty (no collision)
			hashTable[hashIndex] = new LinkedList<KeyValuePair<KeyType, ValueType>>();
			hashTable[hashIndex].add(newPair);
		} else { // hashIndex not empty (collision)
			for (KeyValuePair<KeyType, ValueType> pair : hashTable[hashIndex]) {
				if (key.equals(pair.getKey())) { // Matching keys -> return false
					return false;
				}
			}
			// No matching key found
			hashTable[hashIndex].add(newPair);
		}
		// New pair successfully added
		if (((double)size() / (double)hashTable.length) >= 0.8) rehash(); // rehash if LF >= LFT
		return true;
	}
	
	/**
	 * Private helper method that resizes the hash table array to double the original size and 
	 * rehashes the keys to the new array
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		// Create new table with double size
		LinkedList<KeyValuePair<KeyType, ValueType>>[] newTable = (LinkedList<KeyValuePair<KeyType, 
				ValueType>>[]) new LinkedList[hashTable.length * 2];
		// Rehash all keys to new index
		for (LinkedList<KeyValuePair<KeyType, ValueType>> i : hashTable) {
			if (i == null) continue; // No pairs at this index
			for (KeyValuePair<KeyType, ValueType> pair : i) {
				int newHashIndex = Math.abs(pair.getKey().hashCode())%newTable.length; // Hash function
				if (newTable[newHashIndex] == null) { // hashIndex empty (no collision)
					newTable[newHashIndex] = new LinkedList<KeyValuePair<KeyType, ValueType>>();
					newTable[newHashIndex].add(pair);
				} else { // hashIndex not empty (collision)
					newTable[newHashIndex].add(pair);
				}
			}
		}
		// Reassign new hash table as current table
		hashTable = newTable;
	}
	
	/**
	 * Gets the value for a key-value pair given the corresponding key
	 * 
	 * @param key the key used to search for the value
	 * @return the corresponding value
	 * @throws NoSuchElementException when the key is not found in the hash table
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		int hashIndex = Math.abs(key.hashCode())%hashTable.length; // Hash function
		if (hashTable[hashIndex] == null) throw new NoSuchElementException("Key not found");
		for (KeyValuePair<KeyType, ValueType> pair : hashTable[hashIndex]) {
			if (key.equals(pair.getKey())) { // Key found
				return pair.getValue();
			}
		}
		throw new NoSuchElementException("Key not found");
	}
	
	/**
	 * Returns the current number of key-value pairs stored in the hash table
	 * 
	 * @return the number of key-value pairs stored
	 */
	public int size() {
		int size = 0;
		for (LinkedList<KeyValuePair<KeyType, ValueType>> i : hashTable) {
			if (i == null) continue; // No pairs at this index
			for (KeyValuePair<KeyType, ValueType> pair : i) {
				size++;
			}
		}
		return size;
	}
	
	/**
	 * Determines if a key is contained in the hash table
	 * 
	 * @param key the key to search for
	 * @return true if the key is in the hash table, false otherwise
	 */
	public boolean containsKey(KeyType key) {
		int hashIndex = Math.abs(key.hashCode())%hashTable.length; // Hash function
		if (hashTable[hashIndex] == null) return false;
		for (KeyValuePair<KeyType, ValueType> pair : hashTable[hashIndex]) {
			if (key.equals(pair.getKey())) { // Key found
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes the key-value pair for the specified key from the hash table
	 * 
	 * @param key the key for the key-value pair to remove
	 * @return the value of the key-value pair that was removed or null if the key was not found
	 */
	public ValueType remove(KeyType key) {
		int hashIndex = Math.abs(key.hashCode())%hashTable.length; // Hash function
		if (hashTable[hashIndex] == null) return null;
		for (KeyValuePair<KeyType, ValueType> pair : hashTable[hashIndex]) {
			if (key.equals(pair.getKey())) { // Key to remove found
				hashTable[hashIndex].remove(pair);
				return pair.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Clears the hash table (i.e. deletes all key-value pairs currently stored)
	 */
	public void clear() {
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = null; // Reset every index in hashTable to null
		}
	}

}
