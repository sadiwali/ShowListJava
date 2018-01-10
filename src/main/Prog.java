package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.DatabaseDriverA;
import enumerator.ShowType;
import objects.Show;
import scripts.FileImporter;

public class Prog {
	
	
	
	public static void main (String args[]) {
		if (args.length == 2) {
			System.out.println("importing...");
			// some commands have been passed
			if (args[0].equals("-import")) {
				FileImporter.importFile(args[1]);
			} else {
				System.out.println("Did not understand: " + args[0]);
			}
		} else {
		
			out("Welcome to ShowList Java!");
			//System.out.println(DatabaseDriverA.getInstance().insertNewShow("Soemthing", "very good show", ShowType.ANIME, 12));
		}
		
		

	}
	
	
	
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	


}