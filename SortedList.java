/*
 * SortedList class
 * Created by Jithma Yasiru with CodeRipperZ
 *
*/

class SortedList {
	// initial size of the list
	private int ARRAY_SIZE = 0;
	
	// list implementation using an array
	private ListHouse[] houseList = null;
	
	// current size of the list
	private int listSize = 0;
	
	// constructor
	public SortedList(int array_size) {
		this.ARRAY_SIZE = array_size;
		houseList = new ListHouse[array_size];
	}
	
	// double the array once current size = array size(i.e. when the list is full)
	private void doDouble() {
		if(listSize == ARRAY_SIZE) {
			ARRAY_SIZE *= 2;
			ListHouse[] temp = new ListHouse[ARRAY_SIZE];
			for(int i = 0; i < listSize; i++)
				temp[i].copy(houseList[i]);				
			houseList = temp;
		}
	}
	
	// inserts an element to the list
	public void insert(ListHouse o) {
		doDouble();
		houseList[listSize++] = o;
	}
	
	// not yet implemented
	public void remove() {
		houseList[listSize].copy(new ListHouse());
	}
	
	// finds the first occurance of the list passed as an argument, returns index position if found, else returns -1
	public int find(ListHouse o) {
		for(int i = 0;  i < listSize; i++)
			if(houseList[i].compareTo(o) == 0)
				return i;
		return -1;
	}
	
	// returns an element specified in the position
	public ListHouse findKth(int pos) {
		if(pos >= 0 && pos <= listSize)
				return houseList[pos];
		return null;
	}
	
	// return an array of elements in the list
	public ListHouse[] printList() {
		ListHouse[] tempList = new ListHouse[listSize];
		for(int i = 0; i < listSize; i++) {
			tempList[i] = new ListHouse();
			tempList[i].copy(houseList[i]);
		}
		return tempList;
	}
	
	// will make the list empty
	public void makeEmpty() {
		for(int i = 0; i < listSize; i++)
			houseList[i] = null;
		listSize = 0;
	}
	
	// returns the number of elelments currently in the list
	public int size() {
		return listSize;
	}
}
