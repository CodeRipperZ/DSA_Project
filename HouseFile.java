/*
 * HouseFile Created by W.S.N.Perera
*/

import java.util.Scanner;		// for read from a file object
import java.io.PrintWriter;		// for write to a file object
import java.io.File;

public class HouseFile 
{
	// file name of the file that we are going save to and load from
	private static final String FILE_NAME = "RealEstate.re";
	
	// takes an array of 'ListHouse' objects and write to a .re file
	public static void saveHouseData(ListHouse[] data) throws Exception {
		try {
			File fileObj = new File(FILE_NAME);
			PrintWriter pw = new PrintWriter(fileObj);
			// number of 'ListHouse' objects that we are going to write
			int nObj = data.length;
			// number of 'ListHouse' objects saved in the file is written at the beginning of .re file
			pw.println(nObj);
			// travese through the array and writes each field into a .re file
			for(ListHouse obj : data) {
				pw.println(obj.getLotNumber());
				pw.println(obj.getFirstName());
				pw.println(obj.getLastName());
				pw.println(obj.getPrice());
				pw.println(obj.getSquareFeet());
				pw.println(obj.getNoOfBedrooms());
			}
			pw.close();
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	// check the return type
	public static String loadHouseData (ListHouse[] data) throws Exception {
		try {
			File readfile = new File(FILE_NAME);
			Scanner readCodes = new Scanner(readfile);
			// i said 'DO NOT INSTANTIATE'
			ListHouse [] Listhouse = new ListHouse []; // ERROR, just the declartion of 'ListHouse' array is needed
			int numobj = readCodes.nextInt();
			ListHouse[numobj]; // ERROR, instantiation is wrong, recheck
			
			for (int i=0; i<numobj; i++){
				// check the 'ListHouse' class done by chethaka
				// check the 'example.re' file to see how file structure is organized
				// read appropriate values from the file and assign it to each objects using setter mehtods
				Listhouse[i] = readCodes.nextLine(); 
			}
			
			return Listhouse;
			
			readCodes.close();
		}
			

		catch(Exception ex) {
			throw ex;
		}

	}		
		

}

