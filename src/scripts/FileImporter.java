package scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import database.DatabaseDriverA;
import enumerator.ShowType;

public class FileImporter {
	

	
	public static void importFile(String fileDir) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileDir))) {
			ArrayList<ArrayList<String>> textList = new ArrayList<>();

		    String line;
		    while ((line = br.readLine()) != null) {
		    	if (line.startsWith("#") || line.isEmpty()) {
		    		// skip comments
		    		continue;

		    	}
		    	
		    	ArrayList<String> eachLine = new ArrayList<>();
		    	String date = line.substring(1, 11);
		    	String type = ShowType.ANIME.toString().toLowerCase();
		    	String title = line.substring(12, line.indexOf(" - ")).trim();
		    	String comment = line.substring(line.indexOf(" - ") + 2, line.length() - 5).trim();
		    	String rating = line.substring(line.length() - 5, line.length()).trim();
		    	
		    	//System.out.println(date + ";" + title + ";" + comment + ";" + type + ";" + rating + ";");
		    	eachLine.add(title);
		    	eachLine.add(comment);
		    	eachLine.add(type);
		    	rating = rating.substring(0, 2).replace("/", "");
		    	eachLine.add(rating);
		    	eachLine.add(date);
		    	
		    	textList.add(eachLine);
		       
		    	
		    }
		    
		    // send to DatabaseDriver
		    DatabaseDriverA.getInstance().importFileToDatabase(textList);
		    
		    
		} catch (IOException e) {
			System.out.println("Could not read file. Ensure directory is correct.");
		}
	}

}
