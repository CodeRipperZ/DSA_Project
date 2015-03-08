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
	private static final String TEXTFILENAME = "housefile.re";	
	

	
	public static void saveHouseDataFile(ListHouse[] data) throws Exception
	{
		
		
		
		try 
		{
			File file = new File(TEXTFILENAME);// create new text files
			PrintWriter printWriter = new PrintWriter(file);
            		int oblength = data.length;    
            	
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
		catch (IOException ex) 
		{
			
			throw ex;
			
		}
		catch(Exception e)
		{
			
			throw e;
		}
		
		
		
		}

	
	
	
	

}
