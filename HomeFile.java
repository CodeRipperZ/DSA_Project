/*
 * HouseFile Created by W.S.N.Perera
 */
//package myclass;//change the package name


import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;




public class HouseFile 
{
	
	

	
	public static void saveHouseDataFile(ListHouse[] data) 
	{
		
		File file = new File("housefile.re");// create new text files
		
		try 
		{
			
			PrintWriter printWriter = new PrintWriter(file);
            		int oblength = data.length;    
            		int i; 
            		printWriter.println(oblength);
            		for(ListHouse obj : data) 
            		{
				printWriter.println(obj.getLotnumber);
				printWriter.println(obj.getFirstName);
				printWriter.println(obj.getLastName);
				printWriter.println(obj.getPrice);
				printWriter.println(obj.getSquareFeet);
				printWriter.println(obj.getNumberOfBedrooms);
			}

            		printWriter.close();    
 
	        
			
		} 
		catch (FileNotFoundException ex) 
		{
			
			ex.printStackTrace();
			
		}
		
		
		
		}

	
	
	
	

}
