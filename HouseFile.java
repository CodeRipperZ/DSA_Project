/*
 * HouseFile Created by W.S.N.Perera & A.S Dewpura
 *
 * with CodeRipperZ
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
	
	public static ListHouse[] loadHouseData () throws Exception {
		try {
			File fileObj = new File(FILE_NAME);
			Scanner fRead = new Scanner(fileObj);
			// read number of 'ListHouse' objects saved in the file
			int nObj = fRead.nextInt();
			ListHouse[] temp = new ListHouse[nObj];
			
			// run the loop according to the ListHouse object
			for(int i = 0; i < nObj; i++) {
				temp[i] = new ListHouse();
				temp[i].setLotNumber(fRead.nextLong());
				temp[i].setFirstName(fRead.next());
				temp[i].setLastName(fRead.next());
				temp[i].setPrice(fRead.nextDouble());
				temp[i].setSquareFeet(fRead.nextFloat());
				temp[i].setNoOfBedrooms(fRead.nextInt());
			}
			fRead.close();
			return temp;
		}
		catch(Exception ex) {
			throw ex;
		}
	}
}

