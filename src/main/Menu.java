package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.DatabaseDriverA;

public class Menu {
	
	
	
	public static void mainMenu() throws IOException {
		String inp = "-d";

		out("Welcome to ShowList Java!");
		
		out("There are are " + DatabaseDriverA.getInstance().getNumEntries() + " shows in the database.");
		out("What would you like to do?");
		out("1 \t Add new show\n"
				+ "2 \t Find a show you've watched\n"
				+ "3 \t Browse all shows\n"
				+ "4 \t Recommend a re-watch\n"
				+ "5 \t Close");
		out(": ");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inp = br.readLine();
		
		while (inp.length() != 1 || !("12345".contains(inp))) {
			
			out("Please try again..");
			inp = br.readLine();
		}
	
		switch (Integer.parseInt(inp)) {
			case 1:
				menuNewShow();
				break;
			case 2:
				menuFindShow();
				break;
			case 3:
				menuBrowseAll();
				break;
			case 4: 
				menuRecommend();
				break;
			default:
				break;
		}
		 
		 
	}
	
	public static void menuNewShow() {
		
	}
	
	public static void menuFindShow() {
		
	}
	
	public static void menuBrowseAll() {
		
	}
	
	public static void menuRecommend() {
		
	}
	
	

	/**
	 * Tool function for printing to console.
	 * @param msg the string to print to console.
	 */
	public static void out(String msg) {
		System.out.println(msg);
	}
	


}
