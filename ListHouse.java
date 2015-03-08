/*
* ListHouse Created by C.D.A Gunawardana
*/


class ListHouse implements Listable<ListHouse>
{
	//Declaring the variables
	private long LotNumber =0;
	private String FirstName="NULL";
	private String LastName="NULL";
	private double Price=0;
	private float SquareFeet=0.0f;
	private int NumberOfBedrooms=0

	
	
    	//Get Method for LotNumber
	public long getLotnumber() 
	{
		return LotNumber;
	}

	//Set Method for LotNumber
	public void setLotnumber(long lotnumber) 
	{
		this.LotNumber = lotnumber;
	}

	//Get Method for FirstName
	public String getFirstName()
	{
  		return FirstName;
	}
	
 	//Set Method for FirstName	
	public void setFirstName(String firstname)
	{

		this.FirstName=firstname;
	}

	//Get Method for LastName
	public String getLastName()
	{
  		return LastName;
	}
	
 	//Set Method for LastName	
	public void setLastName(String lastname)
	{

		this.LastName=lastname;
	}	
	
	//Get Method for Price
	public double getPrice() 
	{
		return Price;
	}

	//Set Method for Price
	public void setPrice(double price)
	{
		this.Price = price;
	}

	//Get Method for SquareFeet
	public float getSquareFeet() 
	{
		return SquareFeet;
	}

	//Set Method for SquareFeet
	public void setSquareFeet(float squareFeet)
	{
		this.SquareFeet = squareFeet;
	}

	//Get Method for NumberOfBedrooms
	public int getNumberOfBedrooms() 
	{
		return NumberOfBedrooms;
	}

	//Set Method for NumberOfBedrooms
	public void setNumberOfBedrooms(int numberofbedrooms) 
	{
		this.NumberOfBedrooms = numberofbedrooms;
	}
	
	
	
	/*This Method will compare mostly the LotNumbers of two objects
	 * compareTo Method conditions
	 * If the Integer is equal to the argument then 0 is returned.
	 * If the Integer is less than the argument then -1 is returned.
	 * If the Integer is greater than the argument then 1 is returned.
	 * 
	 */
	//Implements the compareTo method in Listable interface 
	public int compareTo(ListHouse o)
	{	
		if(this.LotNumber < o.LotNumber)
			return -1;
		if(this.LotNumber > o.LotNumber)
			return 1;
			
		return 0;	
	}
	
	//Implements the copy method in Listable interface
	//This Method will copy data from a object one to object two	
	public void copy(ListHouse o)
	{
		this.LotNumber = o.LotNumber;
		this.FirstName = o.FirstName;
		this.LastName = o.LastName;
		this.Price = o.Price;
		this.SquareFeet = o.SquareFeet;
		this.NumberOfBedrooms = o.NumberOfBedrooms;
	}
	
}

//Create Generic Interface Listable 
	interface Listable<E>
	{	
		public int compareTo(E o);		
		public void copy(E o);
	}

