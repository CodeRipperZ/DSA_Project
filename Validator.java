import java.util.regex.*;

class Validator {
	// pattern to check whether strings contain numbers, spaces or special characters
	private static final Pattern ptnSpecChar = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
	
	/*	validating lotNumber
	 *
	 *	conditions
	 *		> lot number cannot be empty, null
	 *		> lot number must be positive and greater than 0
	 *
	*/
	static void validateLotNumber(String input) throws Exception {
		try {
			if(input.trim().length() == 0)
				throw new Exception("Lot number cannot be empty");
			else {
				long lotNo = Long.parseLong(input);
				if(lotNo <= 0)
					throw new Exception("Invalid lot number: " + input);
			}
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Please enter a decimal value as lot number");
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	/* validating first name
	 *
	 *	conditions
	 *		> first name cannot be empty, null
	 *		> first name must have at least 2 valid characters
	 *		> first name must not contain numbers, spaces or special characters like ; , / \ | so on
	 *
	*/
	static void validateFirstName(String input) throws Exception {
		if(input.trim().length() == 0)
			throw new Exception("First name cannot be empty");
		if(input.trim().length() < 2)
			throw new Exception("First name must have at least 2 characters");
		else {
			Matcher m = ptnSpecChar.matcher(input);
			if(m.find() == true)
				throw new Exception("First name cannot contain numbers, spaces or special characters");
		}
	}
	
	/* validating last name
	 *
	 *	conditions
	 *		> last name cannot be empty, null
	 *		> last name must have at least 4 valid characters
	 *		> last name must not contain numbers, spaces or special characters like ; , / \ | so on
	 *
	*/
	static void validateLastName(String input) throws Exception {
		if(input.trim().length() == 0)
			throw new Exception("Last name cannot be empty");
		if(input.trim().length() < 3)
			throw new Exception("Last name must have at least 3 characters");
		else {
			Matcher m = ptnSpecChar.matcher(input);
			if(m.find() == true)
				throw new Exception("Last name cannot contain numbers, spaces or special characters");
		}
	}
	
	/* validating price
	 *
	 *	conditions
	 *		> price cannot be empty, null
	 *		> price must be a positive floating-point value greater than 0
	 *		> price must not contain characters(caught by NumberFormatException)
	 *
	*/
	static void validatePrice(String input) throws Exception {
		try {
			if(input.trim().length() == 0)
				throw new Exception("Price cannot be empty");
			else {
				double price = Double.parseDouble(input);
				if(price <= 0)
					throw new Exception("Price must be a positive value greater than 0");
			}
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Please enter a numeric value as price");
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	/* validating area
	 *
	 *	conditions
	 *		> area cannot be empty, null
	 *		> area must be a positive floating-point value greater than 0
	 *		> area must not contain characters(caught by NumberFormatException)
	 *
	*/
	static void validateArea(String input) throws Exception {
		try {
			if(input.trim().length() == 0)
				throw new Exception("Area cannot be empty");
			else {
				float sqrFt = Float.parseFloat(input);
				if(sqrFt <= 0)
					throw new Exception("Area must be a positive value greater than 0");
			}
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Please enter a numeric value as area");
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	/* validating number of rooms
	 *
	 *	conditions
	 *		> number of rooms cannot be empty, null
	 *		> number of rooms must be a positive integer value greater than 0
	 *		> number of rooms must not contain characters(caught by NumberFormatException)
	 *
	*/
	static void validateRooms(String input) throws Exception {
		try {
			if(input.trim().length() == 0)
				throw new Exception("Number of bedrooms cannot be empty");
			else {
				int noOfBed = Integer.parseInt(input);
				if(noOfBed <= 0)
					throw new Exception("Number of rooms must be a positive value greater than 0");
			}
		}
		catch(NumberFormatException nfe) {
			throw new Exception("Please enter a decimal value as number of bedrooms");
		}
		catch(Exception e) {
			throw e;
		}
	}
}
