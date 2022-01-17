import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestEmployeeHours2 {

    /**
     * This method tests the addEmployee() method in EmployeeHoursBackEnd by checking that (a) new 
     * nodes (representing employees) are inserted in the expected fashion and (b) the expected ID
     * number is returned.
     * 
     * @author Matt Stilling, Back End Developer
     */
    @Test
    public void backendDeveloper_TestAddEmployee() {
        // First insert employees and check that the expected IDs are returned
        EmployeeHoursBackEnd database = new EmployeeHoursBackEnd();
        int id1 = database.addEmployee(new EmployeeData(0, "Bob", "Smith", "bsmith", "012-345-6789",
                "Engineer", 75000, 0)); // Unspecified ID (0) -> first calculated ID should be 1
        assertEquals(id1, 1); 
        int id2 = database.addEmployee(new EmployeeData(0, "Jane", "Doe", "jdoe", "123-456-7890",
                "Marketing", 75000, 0)); // Unspecified ID (0) -> second calculated ID should be 2
        assertEquals(id2, 2);
        int id3 = database.addEmployee(new EmployeeData(4, "Mike", "Johnson", "mjohnson", 
                "234-567-8901", "Finance", 75000, 0)); // Specified ID -> should be 4
        assertEquals(id3, 4);
        int id4 = database.addEmployee(new EmployeeData(0, "Mary", "Jones", "mjones", 
                "345-678-9012", "HR", 75000, 0)); // Unspecified ID -> should calculate 3
        assertEquals(id4, 3);
        
        // Then check that the expected RBT structure was used (i.e. sorted by ID #)
        Iterator<ComparableData> iterator = database.iterator(); // traverses RBT in-order
        assertEquals(iterator.next().getData().ID, 1);
        assertEquals(iterator.next().getData().ID, 2);
        assertEquals(iterator.next().getData().ID, 3);
        assertEquals(iterator.next().getData().ID, 4);
        assertFalse(iterator.hasNext()); // no extra elements
        
        // Check that an IllegalArgumentException is thrown when a null employee is added
        assertThrows(IllegalArgumentException.class, () -> database.addEmployee(null));
        
        // Check that an IllegalARgumentException is thrown when an invalid ID is provided
        assertThrows(IllegalArgumentException.class, () -> database.addEmployee(new EmployeeData(-1, 
                "James", "Brown", "jbrown", "456-789-0123", "PR", 75000, 0))); // negative ID
        assertThrows(IllegalArgumentException.class, () -> database.addEmployee(new EmployeeData(2, 
                "James", "Brown", "jbrown", "456-789-0123", "PR", 75000, 0))); // taken ID
    }
    
    /**
     * This method tests the getEmployee() method in EmployeeHoursBackEnd by checking that the 
     * expected data is returned for the given ID.
     * 
     * @author Matt Stilling, Back End Developer
     */
    @Test
    public void backendDeveloper_TestGetEmployee() {
        EmployeeHoursBackEnd database = new EmployeeHoursBackEnd();
        EmployeeData bobData = new EmployeeData(0, "Bob", "Smith", "bsmith", "012-345-6789", 
                "Engineer", 75000, 0);
        database.addEmployee(bobData); // ID number is 1
        EmployeeData janeData = new EmployeeData(0, "Jane", "Doe", "jdoe", "123-456-7890", 
                "Marketing", 75000, 0);
        database.addEmployee(janeData); // ID number is 2
        
        // Check that calling getEmployee() with the appropriate ID number gets the correct data
        assertEquals(database.getEmployee(1), bobData);
        assertEquals(database.getEmployee(2), janeData);
        
        // Check that calling getEmployee() with an invalid ID number throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> database.getEmployee(3));
    }
    
    /**
     * This method tests the hours system in EmployeeHoursBackEnd by checking that (a) hours can be
     * successfully added to an employee with the given ID using addHours() and (b) the current
     * hours count for every employee is reset when resetAllHours() is called.
     * 
     * @author Matt Stilling, Back End Developer
     */
    @Test
    public void backendDeveloper_TestHoursSystem() {
        EmployeeHoursBackEnd database = new EmployeeHoursBackEnd();
        database.addEmployee(new EmployeeData(0, "Bob", "Smith", "bsmith", "012-345-6789",
                "Engineer", 75000, 0));
        database.addEmployee(new EmployeeData(0, "Jane", "Doe", "jdoe", "123-456-7890", "Marketing",
                75000, 0));
        database.addEmployee(new EmployeeData(4, "Mike", "Johnson", "mjohnson", "234-567-8901", 
                "Finance", 75000, 0));
        // Add hours worked for each employee
        database.addHours(1, 8);
        database.addHours(2, 7);
        database.addHours(4, 4);
        database.addHours(2, 3);
        // Check that each employee has the expected total hour count
        assertEquals(database.getEmployee(1).getHours(), 8);
        assertEquals(database.getEmployee(2).getHours(), 10);
        assertEquals(database.getEmployee(4).getHours(), 4);
        
        // Check that trying to add hours to employee with invalid ID throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> database.addHours(3, 8));
        
        // Check that calling resetAllHours() sets all employee hour counts back to 0
        database.resetAllHours();
        assertEquals(database.getEmployee(1).getHours(), 0);
        assertEquals(database.getEmployee(2).getHours(), 0);
        assertEquals(database.getEmployee(4).getHours(), 0);
    }
    
}
