/*
 * SortedList class
 * Created by Jithma Yasiru with CodeRipperZ
 *
*/

class SortedList {
	public static final int ORDER_ASCENDING = 0;
	public static final int ORDER_DESCENDING = 1;
	
	// size of the array for list impementation using arrays
	private int array_size = 0;
	
	// this variable is used to save the initial list size invoked through the constructor
	// this is used to revert back the list to its initial size
	private int original_size = 0;
	
	// current size of the list
	private int list_size = 0;
	
	// list implementation using an array
	private ListHouse[] houseList = null;
	
	// constructor
	public SortedList(int array_size) {
		this.array_size = array_size;
		this.original_size = array_size;
		houseList = new ListHouse[array_size];
	}
	
	// double the array once current size = array size(i.e. when the list is full)
	private void doDouble() {
		if(list_size == array_size - 1) {
			array_size *= 2;
			ListHouse[] temp = new ListHouse[array_size];
			for(int i = 0; i < list_size; i++) {
				temp[i] = new ListHouse();
				temp[i].copy(houseList[i]);
			}
			houseList = temp;
		}
	}
	
	// inserts an element to the list
	public void insert(ListHouse o) {
		doDouble();
		houseList[list_size++] = o;
	}
	
	// not yet implemented
	public void remove(int pos) {
		if(pos == list_size - 1)
			houseList[pos] = null;
		else {
			for(int i = pos; i < list_size - 1; i++) {
				houseList[i] = new ListHouse();
				houseList[i].copy(houseList[i + 1]);
				houseList[i + 1] = null;
			}
		}
		list_size--;
	}
	
	// finds the first occurance of the list passed as an argument, returns index position if found, else returns -1
	public int find(ListHouse o) {
		for(int i = 0;  i < list_size; i++)
			if(houseList[i].compareTo(o) == 0)
				return i;
		return -1;
	}
	
	// returns an element specified in the position, if not found, null returned
	public ListHouse findKth(int pos) {
		if(pos >= 0 && pos <= list_size)
				return houseList[pos];
		return null;
	}
	
	// return an array of elements in the list
	public ListHouse[] printList() {
		ListHouse[] tempList = new ListHouse[list_size];
		for(int i = 0; i < list_size; i++) {
			tempList[i] = new ListHouse();
			tempList[i].copy(houseList[i]);
		}
		return tempList;
	}
	
	// will make the list empty
	public void makeEmpty() {
		for(int i = 0; i < list_size; i++)
			houseList[i] = null;

		list_size = 0;
		// revert the list back to its inital size, so the memmory can be saved
		array_size = original_size;
		houseList = new ListHouse[original_size];
	}
	
	public ListHouse next(int pos) {
		if(pos >= 0 && pos < list_size)
			return houseList[pos + 1];
		return null;
	}
	
	public ListHouse previous(int pos) {
		if(pos > 0 && pos <= list_size)
			return houseList[pos - 1];
		return null;
	}
	
	// returns the number of elelments currently in the list
	public int getSize() {
		return list_size;
	}
	
	public ListHouse[] sort(int order) {
		ListHouse[] houseArray = this.printList();
		boolean swapped = false;
		for(int i = 0; i < houseArray.length; i++) {
			swapped = false;
			for(int j = 0; j < houseArray.length - 1; j++) {
				switch(order) {
					case ORDER_ASCENDING:
						if(houseArray[j].compareTo(houseArray[j+1]) == -1) {
							ListHouse obj = houseArray[j+1];
							houseArray[j + 1] = houseArray[j];
							houseArray[j] = obj;
							swapped = true;
						}
						break;
					case ORDER_DESCENDING:
						if(houseArray[j].compareTo(houseArray[j+1]) == 1) {
							ListHouse obj = houseArray[j+1];
							houseArray[j + 1] = houseArray[j];
							houseArray[j] = obj;
							swapped = true;
						}
						break;
				}
			}
		}
		return houseArray;
	}
}
