// --== CS400 Project One File Header ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: Blue
// Group: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

/**
 * This is a helper class that groups together key-value pairs into a single object for use in 
 * HashtableMap.java
 * 
 * @author Matt Stilling
 * @param <KeyType> the generic key type
 * @param <ValueType> the generic value type
 */
public class KeyValuePair<KeyType, ValueType> {
	
	private KeyType key; // The key part of the key-value pair
	private ValueType value; // The value part of the key-value pair
	
	/**
	 * Constructs a key-value pair with the provided key and value
	 * 
	 * @param key the key for the key-value pair
	 * @param value the value for the key-value pair
	 */
	public KeyValuePair(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the key for the key-value pair
	 * 
	 * @return the key
	 */
	public KeyType getKey() {
		return key;
	}
	
	/**
	 * Gets the value for the key-value pair
	 * 
	 * @return the value
	 */
	public ValueType getValue() {
		return value;
	}
	
}
