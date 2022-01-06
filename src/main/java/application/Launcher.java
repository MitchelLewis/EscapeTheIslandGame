package main.java.application;

/**
 * Starts the main application, needed to be separated as Maven can't launch classes with
 * dependencies
 * 
 * @author Lewis/Harry
 *
 */
public class Launcher {
	/**
	 * Calls the main method in the Main class.
	 * @param args List of arguments passed in on start but not used.
	 */
    public static void main(String[] args){
        Main.main(args);
    }
}
