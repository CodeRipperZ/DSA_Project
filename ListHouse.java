/*
 * ListHouse Created by C.D.A Gunawardana
*/

//Create Generic Listable Interface
interface Listable<E>
{	
	public int compareTo(E o);		
	public void copy(E o);
}

class ListHouse implements Listable<ListHouse>
{
	//Declaring the variables
	private long lotNo = 0;
	private String fName = "NULL";
	private String lName = "NULL";
	private double price = 0.0;
	private float sqrFeet = 0.0f;
	private int noOfBeds = 0;
	
    	//Get Method for LotNumber
	public long getLotNumber() {
		return this.lotNo;
	}
	
    	//Get Method for FirstName
	public String getFirstName() {
		return this.fName;
	}
	
    	//Get Method for LastName
	public String getLastName() {
		return this.lName;
	}
	
    	//Get Method for Price
	public double getPrice() {
		return this.price;
	}
	
	//Get Method for SquareFeet
	public float getSquareFeet() {
		return this.sqrFeet;
	}
	
	//Get Method for NumberOfBedrooms
	public int getNoOfBedrooms() {
		return this.noOfBeds;
	}
	
	//Set Method for LotNumber
	public void setLotNumber(long lotNo) {
		this.lotNo = lotNo;
	}
	
	//Set Method for FirstName
	public void setFirstName(String fName) {
		this.fName = fName;
	}
	
	//Set Method for LastName
	public void setLastName(String lName) {
		this.lName = lName;
	}
	
	//Set Method for Price
	public void setPrice(double price) {
		this.price = price;
	}
	
	//Set Method for SquareFeet
	public void setSquareFeet(float sqrFeet) {
		this.sqrFeet = sqrFeet;
	}
	
	//Set Method for NumberOfBedrooms
	public void setNoOfBedrooms(int noOfBeds) {
		this.noOfBeds = noOfBeds;
	}
	
	/*
	 * compareTo Method conditions (compares only the lotNumber)
	 * If the ListHouse object 'o' is equal to the argument then 0 is returned.
	 * If the ListHouse object 'o' is less than the argument then -1 is returned.
	 * If the ListHouse object 'o' is greater than the argument then 1 is returned.
	 * 
	 */
	//Implements the compareTo method in Listable interface 
	public int compareTo(ListHouse o) {
		if(o.lotNo > this.lotNo)
			return 1;
		if(o.lotNo < this.lotNo)
			return -1;
		return 0;
	}
	
	//Implements the copy method in Listable interface
	//This Method will copy data from a object one to another	
	public void copy(ListHouse o) {
		this.lotNo = o.lotNo;
		this.fName = o.fName;
		this.lName = o.lName;
		this.price = o.price;
		this.sqrFeet = o.sqrFeet;
		this.noOfBeds = o.noOfBeds;
	}
}
