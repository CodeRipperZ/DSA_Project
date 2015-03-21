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
	
	// we don't need an argument to load from a file
	public static ListHouse[] loadHouseData (ListHouse[] data) throws Exception
		try {
			File readfile = new File(FILE_NAME);
			Scanner readCodes = new Scanner(readfile);
			// i said 'DO NOT INSTANTIATE'
			
			int numobj = readCodes.nextInt();
			
			ListHouse [] Listhouse = new ListHouse [numobj];// correct this to 'ListHouse[] houseObjs'
			
			for (int i=0; i<numobj; i++){
				for(ListHouse object : data){ // this for loop is useless
				// you can access the members of the class by '.' operator
				// so, for example,
				// 	Listhouse[i].setLotNumber(readCodes.nextLong());
				// just correct them and that's it
				Listhouse[i] = readCodes.nextLong(object.setLotNumber());
				Listhouse[i] = readCodes.next(object.setFirstName());
				Listhouse[i] = readCodes.next(object.setLastName());
				Listhouse[i] = readCodes.nextDouble(object.setPrice(price));
				Listhouse[i] = readCodes.nextFloat(object.setSquareFeet());
				Listhouse[i] = readCodes.nextInt(object.setNoOfBedrooms());
				
				}			
			}
			
			return Listhouse;
			
			readCodes.close();
		}
			

		catch(Exception ex) {
			throw ex;
		}

	}		
		

}

