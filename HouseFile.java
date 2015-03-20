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
}
// first put this code block under the class definition, this is way out from the class's right brace :D
// put this block inside above brace
		static String[] ListHouse = new String[99];	// not nessesary
   		static int i = 0;				// not nessasary either
		private static Scanner readCodes;		// not even this, create this inside the method
		// so remove above statements
        	
        	// change method name to 'loadHouseData' ;)
        	// method should return an array of 'ListHouse' objects, check the definition
        	public static String readHouseData() throws Exception {
    		try {
    			// just do as follows
    			// declare a new file object, use 'FILE_NAME' in the constructor for absolute path
    			// declare a scanner object, use the file object declared above in the constructor
    			// declare an array of 'ListHouse' objects, but DO NOT INSTANTIATE!!!
    			// read the first line in the file(which is no. of objects saved in that particular file), assign it to an integer variable
    			// now INSTATIATE 'ListHouse' array, use no. of objects as its length
    			// implement a loop to read through the file, and fill the array with its data
    			
    			readCodes = new Scanner(new File(FILE_NAME)); // create the scanner object inside the method
    			// all the lines below is terribly wrong
    			while(readCodes.hasNext()) {
            		ListHouse[i] = readCodes.nextLine();
            		i++;
    			}
		 	return ListHouse;
		    }
		readCodes.close(); // out of the block, ERROR!!!
    		catch(Exception ex) {
    			throw ex;
    		}

    		
	}
