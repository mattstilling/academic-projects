// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: I added the ComparableData class to my section because we never specified that
//          EmployeeData should be comparable (so the data wrangler didn't know to do this), and I 
//          wanted to add more complexity to my portion of the project.

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Specifies the methods/functionality needed by the back end of the employee hours database.
 * 
 * @author Matt Stilling
 */
interface EmployeeHoursBackEndInterface {
    
    // Adds a new employee (e.g. new hire) and returns their new ID number (calculated in this method)
    public int addEmployee(EmployeeDataInterface employee);
    
    // Returns all info for an employee given their ID for use by the front end
    public EmployeeDataInterface getEmployee(int id);
    
    // Add hours worked for the employee with the given ID
    public void addHours(int id, int hours);
    
    // Use to reset the value of every employee's hours for the week to 0 (i.e. use at end of week)
    public void resetAllHours();
}

/**
 * This class allows EmployeeData objects to be inserted into a RedBlackTree by implementing the 
 * Comparable interface. 
 * 
 * @author Matt Stilling
 */
class ComparableData implements Comparable<ComparableData> {
    
    private EmployeeData data;
    
    /**
     * Constructs a new comparable version of the EmployeeData object "data"
     * 
     * @param data the data to be made comparable
     */
    public ComparableData(EmployeeDataInterface data) {
        this.data = (EmployeeData)data;
    }
    
    /**
     * Gets the normal EmployeeData object of the comparable version
     * 
     * @return the EmployeeData data of this ComparableData object
     */
    public EmployeeData getData() {
        return this.data;
    }
    
    /**
     * Compares the ID numbers of this object's employee and another object's employee
     * 
     * @param other the other employee who this employee's ID is being compared to
     * @return negative integer/zero/positive integer if the ID of this employee is less than/equal 
     *      to/greater than the ID of the other employee
     */
    @Override
    public int compareTo(ComparableData other) {
        return ((Integer)data.getID()).compareTo(other.data.getID());
    }
}

/**
 * This class uses a red black tree to store the database of employee information. It allows the
 * user to add a new employee to the database, get an employee's data, clock hours worked for an
 * employee, and reset the hours worked for all employees.
 * 
 * @author Matt Stilling
 */
public class EmployeeHoursBackEnd implements EmployeeHoursBackEndInterface {
    
    private RedBlackTree<ComparableData> database; // The RBT that holds the employee data
    
    /**
     * Instantiates the red black tree when a new EmployeeHoursBackEnd object is created.
     */
    public EmployeeHoursBackEnd() {
        database = new RedBlackTree<ComparableData>();
    }
    
    /**
     * Used by test methods to ensure the valid storing of employee nodes in the RBT
     * 
     * @return an Iterator that in-order traverses the employee database RBT
     */
    protected Iterator<ComparableData> iterator() {
        return database.iterator();
    }
    
    /**
     * Inserts a new employee (e.g. a new hire) into the database. If the new employee does not have
     * an ID number (i.e. the current value of their ID is 0), a new one is calculated for them.
     * 
     * @param employee the new employee to be inserted into the database; their ID field is 0 if a
     *      new ID needs to be calculated for them
     * @return the new employee's ID number
     * @throws IllegalArgumentException if null is passed as a parameter or a provided ID number is
     *      not valid (negative or already exists in the database)
     */
    @Override
    public int addEmployee(EmployeeDataInterface employee) throws IllegalArgumentException {
        if (employee == null) {
            throw new IllegalArgumentException("Cannot store employees with null reference");
        }
        if (employee.getID() == 0) { // no ID yet, need to calculate
            employee.setID(calculateID());
        } else if (employee.getID() < 0) { // ID provided -> can't be negative number
            throw new IllegalArgumentException("The provided ID of the new employee must be a "
                    + "positive number");
        } else { // ID provided (positive number) -> check that this ID isn't taken
            Iterator<ComparableData> iterator = database.iterator(); // Traverses RBT in-order (sorted)
            while (iterator.hasNext()) {
                ComparableData current = iterator.next();
                if (current.getData().getID() == employee.getID()) { // ID taken
                    throw new IllegalArgumentException("The provided ID of the new employee is "
                            + "already taken");
                }
            }
        }
        database.insert(new ComparableData(employee));
        return employee.getID();
    }
    
    /**
     * Private helper method that calculates the smallest positive integer not currently being used
     * as an ID (for an existing employee).
     * 
     * @return the smallest available ID number
     */
    private int calculateID() {
        int id = 1; // The smallest acceptable ID number
        Iterator<ComparableData> iterator = database.iterator(); // Traverses RBT in-order (sorted)
        while (iterator.hasNext()) {
            ComparableData current = iterator.next();
            if (id < current.getData().getID()) { // id is available as the smallest new ID #
                return id;
            } else {
                id = current.getData().getID() + 1; // set id to next smallest possibly available int
            }
        }
        return id;
    }
    
    /**
     * Gets the data (name, ID, email, phone number, hours worked) for the employee with the given
     * ID.
     * 
     * @param id the ID of the employee to search for
     * @return the data for that employee
     * @throws NoSuchElementException if no employee is found with the matching ID
     */
    @Override
    public EmployeeDataInterface getEmployee(int id) throws NoSuchElementException {
        Iterator<ComparableData> iterator = database.iterator();
        while (iterator.hasNext()) {
            ComparableData current = iterator.next();
            if (current.getData().getID() == id) return current.getData(); // ID match found
        }
        throw new NoSuchElementException("No employee was found with the matching ID");
    }
    
    /**
     * Adds hours worked to the data of the employee with the given ID.
     * 
     * @param id the ID of the employee to add hours to
     * @param hours the number of hours to add
     * @throws NoSuchElementException if no employee is found with the matching ID
     */
    @Override
    public void addHours(int id, int hours) throws NoSuchElementException {
        Iterator<ComparableData> iterator = database.iterator();
        while (iterator.hasNext()) {
            ComparableData current = iterator.next();
            if (current.getData().getID() == id) { // ID match found
                current.getData().addHours(hours);
                return;
            }
        }
        throw new NoSuchElementException("No employee was found with the matching ID");
    }
    
    /**
     * Resets the hours worked of every employee stored in the database to 0. This is intended to be
     * used at the end of a week or pay period to keep the number of hours being tracked manageable.
     */
    @Override
    public void resetAllHours() {
        Iterator<ComparableData> iterator = database.iterator();
        while (iterator.hasNext()) {
            ComparableData current = iterator.next();
            current.getData().resetHours();
        }
    }
}

/**
 * Provides a placeholder object to be used by other project roles (e.g. front-end developer) during
 * the development/testing of this project.
 * 
 * @author Matt Stilling
 */
class EmployeeHoursBackEndPlaceholder implements EmployeeHoursBackEndInterface {
    
    private EmployeeDataInterface onlyEmployee;
    
    @Override
    public int addEmployee(EmployeeDataInterface employee) {
        this.onlyEmployee = employee;
        return onlyEmployee.getID();
    }
    
    @Override
    public EmployeeDataInterface getEmployee(int id) {
        if (onlyEmployee.getID() == id) {
            return onlyEmployee;
        } else {
            return null; 
        }
    }
    
    @Override
    public void addHours(int id, int hours) {
        if (onlyEmployee.getID() == id) {
            //onlyEmployee.addHours(hours);
        }
    }
    
    @Override
    public void resetAllHours() {
        onlyEmployee.resetHours();
    }
}