// --== CS400 Project One File Header ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: Blue
// Group: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

/**
 * This class runs test methods that check the accuracy of the SongSearch program
 * 
 * @author Matt Stilling
 */
public class SongSearchTests {

	/**
	 * The main method runs all the test methods and prints their outcomes
	 * 
	 * @param args unused
	 * @throws Exception if an unexpected exception was thrown
	 */
    public static void main(String[] args) throws Exception {
        // Run All Tests
    	System.out.println("Front End Developer Tests:");
    	System.out.println("testInsertSong: " + frontEndDeveloper_TestInsertSong());
    	System.out.println("testSearch: " + frontEndDeveloper_TestSearch());
    	System.out.println("testMenuHistogram: " + frontEndDeveloper_TestMenuHistogram());
    }

    // Data Wrangler Code Tests

    // Back End Developer Tests

    // Front End Developer Tests
    /**
     * This method tests the accuracy of inserting songs into the database via the run() method
     * in SearchFrontEnd.java
     * 
     * @return true if all tests pass, false otherwise
     */
    public static boolean frontEndDeveloper_TestInsertSong() {
    	// (1) Test all valid input
    	TextUITester tester = new TextUITester("1\nTitle\nArtist\n2000\nQ\n");
    	SearchFrontEndInterface ui = new SearchFrontEnd();
    	ui.run(new SearchBackEndPlaceholder()); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	String output = tester.checkOutput();
    	if (!(output.contains("Now Inserting a New Song into the Database")
    			&& output.contains("Successfully added song to database"))) {
    		System.out.println("Test (1) FAILED");
    		return false;
    	}
    	
    	// (2) Test invalid input (blank lines)
    	tester = new TextUITester("1\n\nTitle\nArtist\n2000\nQ\n"); // "1\n\n" means no song title entered
    	ui = new SearchFrontEnd();
    	ui.run(new SearchBackEndPlaceholder()); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	output = tester.checkOutput();
    	if (!output.contains("(can't be blank)")) { // Indicator that no song title entered
    		System.out.println("Test (2) FAILED");
    		return false;
    	}
    	
    	// (3) Test invalid input (non-integer entered for year)
    	tester = new TextUITester("1\nTitle\nArtist\nYEAR\n2000\nQ\n"); // "YEAR" entered in place of year number
    	ui = new SearchFrontEnd();
    	ui.run(new SearchBackEndPlaceholder()); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	output = tester.checkOutput();
    	if (!output.contains("(number only)")) { // Indicator that invalid input entered for year
    		System.out.println("Test (3) FAILED");
    		return false;
    	}
    	
    	return true; // All tests passed
    }
    
    /**
     * This method tests the accuracy of both searching for song titles and searching for 
     * artists via the run() method in SearchFrontEnd.java
     * 
     * @return true if all tests pass, false otherwise
     */
    public static boolean frontEndDeveloper_TestSearch() {
    	// (1) Search for song titles with all valid input (should find song)
    	TextUITester tester = new TextUITester("2\nSong A Vowel\nQ\n"); // Search for song
    	SearchFrontEndInterface ui = new SearchFrontEnd();
    	SearchBackEndPlaceholder backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song to search for
    	ui.run(backEnd);
    	String output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Song Titles")
    			&& output.contains("Matching song titles:")
    			&&output.contains("Song A Vowel"))) {
    		System.out.println("Test (1) FAILED");
    		return false;
    	}
    	
    	// (2) Search for song titles with all valid input (should not find song)
    	tester = new TextUITester("2\nInvalid Title\nQ\n"); // Search for song
    	ui = new SearchFrontEnd();
    	backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song to search for
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Song Titles")
    			&& output.contains("No song titles found with the given keywords"))) {
    		System.out.println("Test (2) FAILED");
    		return false;
    	}
    	
    	// (3) Search for song titles with invalid input
    	tester = new TextUITester("2\n \nInvalid Title\nQ\n"); // "\n \n" should re-prompt for title
    	ui = new SearchFrontEnd();
    	backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song to search for
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Song Titles")
    			&& output.contains("(can't be blank)"))) {
    		System.out.println("Test (3) FAILED");
    		return false;
    	}
    	
    	// (4) Search for artists with all valid input (should find artist)
    	tester = new TextUITester("3\nArtist X\nQ\n"); // Search for artist
    	ui = new SearchFrontEnd();
    	backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song data to search for
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Artists")
    			&& output.contains("Matching artists:")
    			&& output.contains("Artist X"))) {
    		System.out.println("Test (4) FAILED");
    		return false;
    	}
    	
    	// (5) Search for artists with all valid input (should not find artist)
    	tester = new TextUITester("3\nArtist Y\nQ\n"); // Search for artist
    	ui = new SearchFrontEnd();
    	backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song data to search for
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Artists")
    			&& output.contains("No arists found with the given keywords"))) {
    		System.out.println("Test (5) FAILED");
    		return false;
    	}
    	
    	// (6) Search for artists with invalid input
    	tester = new TextUITester("3\n  \nArtist X\nQ\n"); // "\n  \n" should re-prompt for artist
    	ui = new SearchFrontEnd();
    	backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // Add song data to search for
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Now Searching for Artists")
    			&& output.contains("(can't be blank)"))) {
    		System.out.println("Test (6) FAILED");
    		return false;
    	}
    	
    	return true; // All tests passed
    }
    
    /**
     * This method tests the functionality of the main menu and the accuracy of the histograms 
     * created in the run() method of SearchFrontEnd.java
     * 
     * @return true if all tests pass, false otherwise
     */
    public static boolean frontEndDeveloper_TestMenuHistogram() {
    	// Menu tests
    	// (1) Test that only input outside expected range re-prompts user
    	TextUITester tester = new TextUITester("invalid\nQ\n");
    	SearchFrontEndInterface ui = new SearchFrontEnd();
    	ui.run(new SearchBackEndPlaceholder()); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	String output = tester.checkOutput();
    	if (!(output.contains("Invalid Command, Enter a Command ('Q' to quit):"))) {
    		System.out.println("Test (1) FAILED");
    		return false;
    	}
    	// (2) Test that 'q' also quits the program (designed to be case-insensitive)
    	tester = new TextUITester("q\n");
    	ui = new SearchFrontEnd();
    	ui.run(new SearchBackEndPlaceholder()); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	output = tester.checkOutput();
    	if (!(output.contains("Program quit; thanks for using SongSearch!"))) {
    		System.out.println("Test (2) FAILED");
    		return false;
    	}
    	
    	// Histogram test
    	// (3) Successfully constructed histogram
    	tester = new TextUITester("4\nSong\nQ\n"); // Create histogram
    	ui = new SearchFrontEnd();
    	SearchBackEndPlaceholder backEnd = new SearchBackEndPlaceholder(); // Placeholder used, SearchBackEnd not completed at time of writing tests
    	backEnd.addSong(new SongDataPlaceholderA()); // The song the histogram should include
    	ui.run(backEnd);
    	output = tester.checkOutput();
    	if (!(output.contains("Histogram for songs containing \"Song\":")
    			&& output.contains("Year: Count (* represents one matching song)\n" // This is what the histogram is expected to look like
    							 + "---------------------------------------------\n"
    							 + "1900: *\n"))) {
    		System.out.println("Test (3) FAILED");
    		return false;
    	}
    	
    	return true; // All tests passed
    }

    // Integration Manager Tests


}