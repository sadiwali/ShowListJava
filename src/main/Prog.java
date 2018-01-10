package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DatabaseClass;
import enumerator.ShowType;
import objects.Show;

public class Prog {
	
	
	
	public static void main (String args[]) {
		
		out("Welcome to ShowList Java!");
		DatabaseClass dbC = new DatabaseClass();
		
		try {
			dbC.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	
	
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	


}
