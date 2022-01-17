// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: none

// Interface
interface AirportInterface {
    public String getName();
    public String getCode();
    public String getLocation();
}

/**
 * This class implements an airport object that stores information about an airport. To be used by
 * the back end as the vertices in a graph.
 * 
 * @author Matt Stilling
 */
public class Airport implements AirportInterface {
    
    private String name;
    private String code; // Airport code, e.g. LAX
    private String location;
    
    /**
     * Constructs a new airport with the given information
     * 
     * @param name the name of the airport
     * @param code the IATA code for the airport (e.g. LAX)
     * @param location the location of the airport in the format: City, State
     */
    public Airport(String name, String code, String location) {
        this.name = name;
        this.code = code;
        this.location = location;
    }
    
    /**
     * Returns the airport's name
     * 
     * @return the name of the airport
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Returns the three-letter IATA code for the airport (e.g. LAX)
     * 
     * @return the airport code
     */
    @Override
    public String getCode() {
        return code;
    }
    
    /**
     * Returns the airport's location; typically in the format: City, State
     * 
     * @return the location of the airport
     */
    @Override
    public String getLocation() {
        return location;
    }
}

// Placeholders
class AirportPlaceholderLAX implements AirportInterface {
    @Override
    public String getName() { return "Los Angeles International Airport"; }
    @Override
    public String getCode() { return "LAX"; }
    @Override
    public String getLocation() { return "Los Angeles, CA"; }
}

class AirportPlaceholderMSN implements AirportInterface {
    @Override
    public String getName() { return "Dane County Regional Airport"; }
    @Override
    public String getCode() { return "MSN"; }
    @Override
    public String getLocation() { return "Madison, WI"; }
}
