// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: none

//Interface
interface FlightInterface {
    public AirportInterface getOrigin();
    public AirportInterface getDestination();
    public int getTime();
}

/**
 * This class implements a flight object that stores information about a flight between two airports.
 * To be used by the back end as the edge connecting two airport vertices in a graph, where the edge
 * weight is the flight time in minutes.
 * 
 * @author Matt Stilling
 */
public class Flight implements FlightInterface {

    private AirportInterface origin;
    private AirportInterface destination;
    private int flightTime;
    
    /**
     * Constructs a new flight with the given information
     * 
     * @param origin the origin airport
     * @param destination the destination airport
     * @param flightTime the flight time in minutes
     */
    public Flight(AirportInterface origin, AirportInterface destination, int flightTime) {
        this.origin = origin;
        this.destination = destination;
        this.flightTime = flightTime;
    }
    
    /**
     * Return the flight's origin
     * 
     * @return the Airport object containing the origin airport
     */
    @Override
    public AirportInterface getOrigin() {
        return origin;
    }
    
    /**
     * Return the flight's destination
     * 
     * @return the Airport object containing the destination airport
     */
    @Override
    public AirportInterface getDestination() {
        return destination;
    }
    
    /**
     * Return the time of the flight (i.e. weight between the Airport nodes)
     * 
     * @return the flight time in minutes
     */
    @Override
    public int getTime() {
        return flightTime;
    }
}

// Placeholder
class FlightPlaceholder implements FlightInterface {
    @Override
    public AirportInterface getOrigin() { return new AirportPlaceholderMSN(); }
    @Override
    public AirportInterface getDestination() { return new AirportPlaceholderLAX(); }
    @Override
    public int getTime() { return 270; }
}
