// --== CS400 Project One File Header ==--
// Name: Matt Stilling
// Email: mstilling@wisc.edu
// Team: Blue
// Group: CN
// TA: Evan
// Lecturer: Florian
// Notes to Grader: (none)

import java.util.List;
import java.util.Scanner;

// interface (implemented with proposal)
interface SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine);
}

// public class (implemented primarily in final app week)
/**
 * This class implements a text-based user interface that allows the user of
 * SongSearch to interact with the database of songs in a simple, intuitive way.
 * 
 * @author Matt Stilling
 */
public class SearchFrontEnd implements SearchFrontEndInterface {

    /**
     * This method contains all the print statements and user-input mechanisms needed for the user
     * to interact indirectly with the database. It allows the user to perform 4 actions (+ quit): 
     *      1. Insert a new song into the database 
     *      2. Search for song titles by words in those titles 
     *      3. Search for artists by name
     *      4. Display the years of songs with a user-input keyword in their title as a histogram
     * 
     * @param searchEngine the back end used by the front end to access the database of songs
     */
    @Override
    public void run(SearchBackEndInterface searchEngine) {
        Scanner in = new Scanner(System.in); // the Scanner used for user input

        System.out.println("Welcome to SongSearch");
        boolean done = false;
        while (!done) {
            // Print the command menu
            System.out.println("\nCommand Menu:");
            System.out.println("'1' - Insert New Song into Database");
            System.out.println("'2' - Search For Song Titles by Words in those Titles");
            System.out.println("'3' - Search For Artists by Name");
            System.out.println("'4' - Display Years of Songs with Keyword in Title as Histogram");
            System.out.println("'Q' - Quit");
            System.out.print("Enter a Command: ");
            char command = in.next().toUpperCase().charAt(0);
            while (!(command == '1' || command == '2' || command == '3' || command == '4' 
                    || command == 'Q')) { // input validation
                System.out.print("Invalid Command, Enter a Command ('Q' to quit): ");
                command = in.next().toUpperCase().charAt(0);
            }
            System.out.println();
            in.nextLine();

            if (command == '1') {
                System.out.println("Now Inserting a New Song into the Database");
                System.out.print("Please enter the song's title: ");
                String title = in.nextLine().trim();
                while (title == null || title.equals("")) { // input validation
                    System.out.print("Please enter the song's title (can't be blank): ");
                    title = in.nextLine().trim();
                }

                System.out.print("Please enter the song's artist: ");
                String artist = in.nextLine().trim();
                while (artist == null || artist.equals("")) { // input validation
                    System.out.print("Please enter the song's artist (can't be blank): ");
                    artist = in.nextLine().trim();
                }

                System.out.print("Please enter the year the song was released: ");
                int year;
                while (!in.hasNextInt()) { // input validation
                    System.out.print("Please enter the year the song was released (number only): ");
                    in.next();
                }
                year = in.nextInt();
                SongData song = new SongData(title, artist, year);
                if (searchEngine.containsSong(song)) {
                    System.out.println("Failed to add song (song already in database");
                } else {
                    searchEngine.addSong(song);
                    System.out.println("Successfully added song to database");
                }

            } else if (command == '2') {
                System.out.println("Now Searching for Song Titles");
                System.out.print("Please enter the song's title: ");
                String titleWord = in.nextLine().trim();
                while (titleWord == null || titleWord.equals("")) { // input validation
                    System.out.print("Please enter the song's title (can't be blank): ");
                    titleWord = in.nextLine().trim();
                }
                List<String> matchingTitles = searchEngine.findTitles(titleWord);
                if (matchingTitles == null || matchingTitles.isEmpty()) {
                    System.out.println("No song titles found with the given keywords");
                } else {
                    System.out.println("Matching song titles:");
                    for (String title : matchingTitles) {
                        System.out.println(title);
                    }
                }

            } else if (command == '3') {
                System.out.println("Now Searching for Artists");
                System.out.print("Please enter the name of the artist: ");
                String titleWord = in.nextLine().trim();
                while (titleWord == null || titleWord.equals("")) { // input validation
                    System.out.print("Please enter the name of the artist (can't be blank): ");
                    titleWord = in.nextLine().trim();
                }
                List<String> matchingArtists = searchEngine.findArtists(titleWord);
                if (matchingArtists == null || matchingArtists.isEmpty()) {
                    System.out.println("No arists found with the given keywords");
                } else {
                    System.out.println("Matching artists:");
                    for (String artist : matchingArtists) {
                        System.out.println(artist);
                    }
                }

            } else if (command == '4') {
                System.out.println("Now Creating Histogram for Songs with Keyword in Title");
                System.out.print("Please enter the keyword/title you would like to search for: ");
                String titleWord = in.nextLine().trim();
                while (titleWord == null || titleWord.equals("")) { // input validation
                    System.out.print("Please enter the keyword/title you would like to search for " 
                            + "(can't be blank): ");
                    titleWord = in.nextLine().trim();
                }

                // Find the data needed to construct the histogram
                int minYear = 0;
                int maxYear = 0;
                for (int i = 1900; i < 2022; i++) {
                    int numSongsInYear = searchEngine.findNumberOfSongsInYear(titleWord, i);
                    if (numSongsInYear == 0) {
                        continue;
                    } else {
                        maxYear = i; // iterating up, so this is the new maxYear
                        if (minYear == 0) minYear = i; // minYear == 0 only when no previous years
                                                       // found with matching keywords
                    }
                }
                // Construct the histogram
                if (minYear == 0) { // no matching songs found -> do NOT construct histogram
                    System.out.println("No matching songs found");
                } else {
                    System.out.println("Histogram for songs containing \"" + titleWord + "\":\n");
                    System.out.println("Year: Count (* represents one matching song)");
                    System.out.println("---------------------------------------------");
                    for (int i = minYear; i <= maxYear; i++) {
                        System.out.print(i + ": ");
                        int numSongs = searchEngine.findNumberOfSongsInYear(titleWord, i);
                        for (int j = 0; j < numSongs; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                }

            } else { // command is 'Q' -> quit program
                done = true;
            }
        }
        System.out.println("Program quit; thanks for using SongSearch!");
        in.close();
    }

}

//placeholder (implemented with proposal, and possibly added to later)
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }
}
