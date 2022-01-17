// --== CS400 File Header Information ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: none

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Interface
interface AirportsLoaderInterface {
    
    // Load airports (i.e. vertices)
    public List<AirportInterface> loadAirports(String airportsCSV) throws FileNotFoundException;
    
    // Load flights (i.e. edges) between existing airports
    public List<FlightInterface> loadFlights(String flightsCSV, 
           AirportPathBackEndInterface backend) throws FileNotFoundException;
}

/**
 * This class provides methods to load airport and flight data from csv files into lists of Airport
 * and Flight objects. Intended to be used by the back end to create the starting graph for a flight
 * network.
 * 
 * @author Matt Stilling
 */
public class AirportsLoader implements AirportsLoaderInterface {
    
    /**
     * Creates a list of Airport objects given a csv containing airport data in the format:
     * name,code,location
     * 
     * @param airportsCSV the name of (i.e. path to) the csv file containing the airport data
     * @return list of Airport objects created from given csv file
     * @throws FileNotFoundException if the file with the path airportsCSV cannot be found
     */
    @Override
    public List<AirportInterface> loadAirports(String airportsCSV) throws FileNotFoundException {
        List<AirportInterface> airports = new LinkedList<>();
        Scanner scnr = new Scanner(new File(airportsCSV));
        scnr.nextLine(); // Remove first line with column labels
        while (scnr.hasNextLine()) {
            try {
                String[] data = scnr.nextLine().split(",");
                airports.add(new Airport(data[0], data[1], data[2]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scnr.close();
        return airports;
    }
    
    /**
     * Creates a list of Flight objects given a csv containing flight data in the format:
     * originID,destinationID,flightTime
     * 
     * @param flightsCSV the name of (i.e. path to) the csv file containing the flight data
     * @param backend the back end object that these flights are to be loaded into; needed to access
     *      Airport information when creating Flight objects
     * @return list of Flight objects created from given csv file
     * @throws FileNotFoundException if the file with the path flightsCSV cannot be found
     */
    @Override
    public List<FlightInterface> loadFlights(String flightsCSV, 
            AirportPathBackEndInterface backend) throws FileNotFoundException {
        List<FlightInterface> flights = new LinkedList<>();
        Scanner scnr = new Scanner(new File(flightsCSV));
        scnr.nextLine(); // Remove first line with column labels
        while (scnr.hasNextLine()) {
            try {
                String[] data = scnr.nextLine().split(",");
                flights.add(new Flight(backend.getAirport(data[0]), backend.getAirport(data[1]), 
                        Integer.parseInt(data[2])));
            } catch (Exception e) { // Catch any error from getting Airport or parsing int here
                e.printStackTrace();
            }
        }
        scnr.close();
        return flights;
    }
    
}

// Placeholder
class AirportsLoaderPlaceholder implements AirportsLoaderInterface {
    
    @Override
    public List<AirportInterface> loadAirports(String airportsCSV) throws FileNotFoundException {
        List<AirportInterface> airports = new LinkedList<>();
        airports.add(new AirportPlaceholderLAX());
        airports.add(new AirportPlaceholderMSN());
        return airports;
    }
    
    @Override
    public List<FlightInterface> loadFlights(String flightsCSV, 
            AirportPathBackEndInterface backend) throws FileNotFoundException {
        List<FlightInterface> flights = new LinkedList<>();
        flights.add(new FlightPlaceholder());
        return flights;
    }
    
}
