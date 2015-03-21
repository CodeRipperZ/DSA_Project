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
	
	/* loadHouseData created by A.S Dewpura */
	
	// we don't need an argument to load from a file
	public static ListHouse[] loadHouseData () throws Exception
		try {
			File readfile = new File(FILE_NAME);
			Scanner readCodes = new Scanner(readfile);
			
			int numobj = readCodes.nextInt();
			
			ListHouse [] houseObjs = new ListHouse [numobj];
			
			for (int i=0; i<numobj; i++){
			
				houseObjs[i].setLotNumber(readCodes.nextLong());
				houseObjs[i].setFirstName(readCodes.next());
				houseObjs[i].setLastName(readCodes.next());
				houseObjs[i].setPrice(readCodes.nextDouble());
				houseObjs[i].setSquareFeet(readCodes.nextFloat());
				houseObjs[i].setNoOfBedrooms(readCodes.nextInt())
				
							
			}
			
			return houseObjs;
			
			readCodes.close();
		}
			

		catch(Exception ex) {
			throw ex;
		}

	}		
		

}

