import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.console.ConsoleLauncher;

public class AirportPathTests {
    
    // Tests for Data Wrangler (Matt Stilling)
    
    /**
     * Tests the constructor and getter methods in the Airport and Flight data classes.
     * 
     * @author Matt Stilling, Data Wrangler
     */
    @Test
    public void dataWrangler_TestDataClasses() {
        // Create new Airport object
        Airport chicago = new Airport("O'Hare International Airport", "ORD", "Chicago, IL");
        // And test the getter methods for Airport on that object
        assertEquals(chicago.getName(), "O'Hare International Airport");
        assertEquals(chicago.getCode(), "ORD");
        assertEquals(chicago.getLocation(), "Chicago, IL");
        
        // Create new Flight object
        Airport atlanta = new Airport("Hartsfield Jackson Atlanta International Airport", "ATL", "Atlanta, GA");
        Flight ordToAtl = new Flight(chicago, atlanta, 120);
        // And test the getter methods for Flight on that object
        assertEquals(ordToAtl.getOrigin(), chicago);
        assertEquals(ordToAtl.getDestination(), atlanta);
        assertEquals(ordToAtl.getTime(), 120);
    }
    
    /**
     * Tests loading the airports from Airports.csv using the loadAirports() method in the 
     * AirportsLoader class.
     * 
     * @author Matt Stilling, Data Wrangler
     */
    @Test
    public void dataWrangler_TestLoadAirports() {
        // First load airports
        AirportsLoader loader = new AirportsLoader();
        List<AirportInterface> airports = null;
        try {
            airports = loader.loadAirports("./src/Airports.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Then check that airports is as expected
        assertEquals(airports.get(0).getCode(), "ORD");
        assertEquals(airports.get(5).getCode(), "CLT");
        assertEquals(airports.get(24).getCode(), "SLC");
        assertEquals(airports.size(), 25); // researched top 25 airports in the US
    }
    
    /**
     * Tests loading the flights from Flights.csv using the loadFlights() method in the 
     * AirportsLoader class.
     * 
     * @author Matt Stilling, Data Wrangler
     */
    @Test
    public void dataWrangler_TestLoadFlights() {
        // First load flights
        AirportsLoader loader = new AirportsLoader();
        // Anonymous class used to ensure accuracy of loading without requiring accuracy of back end
        AirportPathBackEndInterface backend = new AirportPathBackEndInterface() {
            @Override
            public Airport getAirport(String id) {
                Airport airport = new Airport("x", id, "z"); // Creates Airport object with correct id
                return airport;
            }
            // Other methods required by interface
            public int getCost(String start, String end) { return 0; }
            public boolean addFlight(String first, String second, int minutes) { return false; }
            public List<String> getPath(String start, String end) { return null; }
        };
        List<FlightInterface> flights = null;
        try {
            flights = loader.loadFlights("./src/Flights.csv", backend);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Then check that flights is as expected
        assertEquals(flights.get(0).getOrigin().getCode(), "ORD");
        assertEquals(flights.get(10).getDestination().getCode(), "TPA");
        assertEquals(flights.get(148).getTime(), 155);
        assertEquals(flights.size(), 149); // researched 149 flights
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
                "-cp",
                classPath,
                "--include-classname=.*",
                "--select-class=" + className };
        ConsoleLauncher.main(arguments);
    }

}