package main;

import java.io.IOException;

import database.DatabaseDriverA;
import scripts.FileTools;

public class Prog {
	
	
	
	public static void main (String args[]) {
		if (args.length == 2) {
			// some commands have been passed
			if (args[0].equals("-i")) {
				out("Comments in file will not be carried over to database.");
				// read database from a specified file
				FileTools.importFile(args[1]);
			} else if (args[0].equals("-e")) {
				// write the database to a specified file

				FileTools.outputFile(args[1]);
				
			} else {
				out("Did not understand: '" + args[0] + "'. Faulty command. ");
			}
			
		} else {
			// run program normally
			try {
				Menu.mainMenu();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}
		
		

	}
	
	
	/**
	 * Tool function for printing to console.
	 * @param msg the string to print to console.
	 */
	public static void out(String msg) {
		System.out.println(msg);
	}
	
	
	

}
