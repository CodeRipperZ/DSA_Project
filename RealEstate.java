/* RealEstate Class
 *
 * Created By: Hashan Chandika with CodeRipperZ
 *
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class RealEstate extends JFrame implements ActionListener {
	/* All the controls in the JFrame are added to its 'ContentPane', which is layed
	 * out according to 'BoxLayout'. Controls are grouped in Containers considering
	 * their behaviour. Containers obtain GridLayout.
	*/
	// panLog contains lblLog, which is a JLabel that program writes its messages
	private Container panLog = new Container();
	// panOperations contains controls related to List operations.(Add, Remove, Reset etc)
	private Container panOperations = new Container();
	// panNavigation contains controls which helps the user to navigate through the list.(Next, Prev)
	private Container panNavigation = new Container();
	// panContent contains data-entry controls(labels and textfields)
	private Container panContent = new Container();
	
	// program writes the messages
	private JLabel lblLog = new JLabel("Ready");
	// program show where the user is browsing inside the list
	private JLabel lblItem = new JLabel("Empty List");
	
	// buttons for operating on the list of 'ListHouse' objects
	private JButton btnAdd = new JButton("Add");
	private JButton btnRemove = new JButton("Remove");
	private JButton btnReset = new JButton("Reset");
	private JButton btnFind = new JButton("Find");
	
	private JButton btnNext = new JButton("Next");		// to display Next element in the list
	private JButton btnPrev = new JButton("Previous");	// to display Previous element in the list
	private JButton btnLoad = new JButton("Load");		// to load data to the list from a file
	private JButton btnSave = new JButton("Save");		// to save data in the list to a file
	
	// labels
	private JLabel lblLotNo = new JLabel("Lot Number:");
	private JLabel lblFName = new JLabel("First Name:");
	private JLabel lblLName = new JLabel("Last Name:");
	private JLabel lblPrice = new JLabel("Price:");
	private JLabel lblSqrF = new JLabel("Square Feet:");
	private JLabel lblBedNo = new JLabel("No. of Bedrooms:");
	
	// textfields
	private JTextField txtLotNo = new JTextField();
	private JTextField txtFName = new JTextField();
	private JTextField txtLName = new JTextField();
	private JTextField txtPrice = new JTextField();
	private JTextField txtSqrF = new JTextField();
	private JTextField txtBedNo = new JTextField();
	
	private int list_cursor = 0;								// to navigate through the list
	private final SortedList CORE_LIST = new SortedList(3);		// List ADT to store 'ListHouse' objects
	
	// initializes controls on the frame
	private void initFrame() {
		// text fields
		txtLotNo.setEditable(false);
		txtFName.setEditable(false);
		txtLName.setEditable(false);
		txtPrice.setEditable(false);
		txtSqrF.setEditable(false);
		txtBedNo.setEditable(false);
		
		// buttons
		btnPrev.setFocusable(false);
		btnNext.setFocusable(false);
		
		// status area
		// panLog
		BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
		lblLog.setBorder(bb);
		lblItem.setBorder(bb);		
		lblLog.setHorizontalAlignment(JLabel.CENTER);
		lblItem.setHorizontalAlignment(JLabel.CENTER);
				
		panLog.setLayout(new GridLayout(1, 2, 1, 0));
		panLog.add(lblLog);
		panLog.add(lblItem);
		
		//panOperations
		panOperations.setLayout(new GridLayout(2, 2, 5, 3));
		panOperations.add(btnAdd);
		panOperations.add(btnRemove);
		panOperations.add(btnReset);
		panOperations.add(btnFind);
		
		//panNavigation
		panNavigation.setLayout(new GridLayout(2, 2, 5, 3));
		panNavigation.add(btnSave);
		panNavigation.add(btnLoad);
		panNavigation.add(btnPrev);
		panNavigation.add(btnNext);
		
		//alignments
		lblLotNo.setHorizontalAlignment(JLabel.RIGHT);
		lblFName.setHorizontalAlignment(JLabel.RIGHT);
		lblLName.setHorizontalAlignment(JLabel.RIGHT);
		lblPrice.setHorizontalAlignment(JLabel.RIGHT);
		lblSqrF.setHorizontalAlignment(JLabel.RIGHT);
		lblBedNo.setHorizontalAlignment(JLabel.RIGHT);
		
		//panContent
		panContent.setLayout(new GridLayout(6, 2, 10, 5));
		panContent.add(lblLotNo);
		panContent.add(txtLotNo);
		panContent.add(lblFName);
		panContent.add(txtFName);
		panContent.add(lblLName);
		panContent.add(txtLName);
		panContent.add(lblPrice);
		panContent.add(txtPrice);
		panContent.add(lblSqrF);
		panContent.add(txtSqrF);
		panContent.add(lblBedNo);
		panContent.add(txtBedNo);
		
		//registring action listeners
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnReset.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		btnFind.addActionListener(this);
		
		//add to Frame
		add(panNavigation);
		add(panContent);
		add(panOperations);
		add(panLog);
	}

	// constructor constructs the JFrame
	public RealEstate() {
		initFrame();						// initialize frame contents
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	// set frame layout
		setTitle("Real Estate Program");	// set frame title
		setSize(300, 420);					// set frame size
		setResizable(false);				// disable resizing the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);									// set default exit behaviour
		setLocationRelativeTo(null);													// set frame location
		setVisible(true);					// display frame
		
		switchEditMode(false);
	}
	
	public static void main (String[] args) {
		// set look and feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e){
			System.out.println("No Look & Feel Loaded!...");
			System.out.println("Exception: " + e.getMessage());
		}
		new RealEstate();
	}
	
	// ### helpers
	// checks the listpointer whether to disable navigation buttons or not
	private void checkCursor() {
		int list_size = CORE_LIST.getSize();
		btnPrev.setEnabled((CORE_LIST.previous(list_cursor) == null) ? false : true);
		btnNext.setEnabled((CORE_LIST.next(list_cursor) == null) ? false : true);
		btnRemove.setEnabled((list_size > 0 ? true : false));
		btnFind.setEnabled((list_size > 0 ? true : false));
		btnReset.setEnabled((list_size > 0 ? true : false));
		
		if(list_size == 0) {
			lblLog.setText("Ready");
			lblItem.setText("Empty List");
		}
	}
	
	// collects user entered data from textfields and returns a ListHouse object, so it can be easily add to the sorted list
	private ListHouse getItem() throws Exception {
		ListHouse temp = new ListHouse();
		String lotNo = txtLotNo.getText();
		String fName = txtFName.getText();
		String lName = txtLName.getText();
		String price = txtPrice.getText();
		String sqrFt = txtSqrF.getText();
		String rooms = txtBedNo.getText();
		
		Validator.validateLotNumber(lotNo);
		temp.setLotNumber(Long.parseLong(lotNo));
		Validator.validateFirstName(fName);
		temp.setFirstName(fName);
		Validator.validateLastName(lName);
		temp.setLastName(lName);
		Validator.validatePrice(price);
		temp.setPrice(Double.parseDouble(price));
		Validator.validateArea(sqrFt);
		temp.setSquareFeet(Float.parseFloat(sqrFt));
		Validator.validateRooms(rooms);
		temp.setNoOfBedrooms(Integer.parseInt(rooms));
		
		return temp;
	}
	
	// displays a ListHouse object's data fields in the textfields
	private void displayItem(ListHouse obj) {
		if(obj != null) {
			txtLotNo.setText(String.format("%06d", obj.getLotNumber()));
			txtFName.setText(obj.getFirstName());
			txtLName.setText(obj.getLastName());
			txtPrice.setText(String.format("%.2f", obj.getPrice()));
			txtSqrF.setText(String.format("%.2f", obj.getSquareFeet()));
			txtBedNo.setText(String.format("%d", obj.getNoOfBedrooms()));
		}
		else
			clearTextFields();
	}
	
	// ### Event Handling
	// write messages to the log, so the user know what's going on
	private void writeLog(String txt) {
		lblLog.setText(txt);
		lblItem.setText(String.format("Item %d of %d", reList.size() == 0 ? listPointer : listPointer + 1, reList.size()));
	}
	
	// clears all the textfields when in editmode
	private void clearTextFields() {
		txtLotNo.setText(null);
		txtFName.setText(null);
		txtLName.setText(null);
		txtPrice.setText(null);
		txtSqrF.setText(null);
		txtBedNo.setText(null);
	}
	
	// allows the program to toggle between edit mode and normal mode
	private void switchEditMode(boolean flag) {
		btnSave.setEnabled(!flag);
		btnLoad.setEnabled(!flag);
		btnNext.setEnabled(!flag);
		btnPrev.setEnabled(!flag);
		btnFind.setEnabled(!flag);
		
		txtLotNo.setEditable(flag);
		txtFName.setEditable(flag);
		txtLName.setEditable(flag);
		txtPrice.setEditable(flag);
		txtSqrF.setEditable(flag);
		txtBedNo.setEditable(flag);
		txtLotNo.requestFocus();
		
		if(flag == false)
			checkPointer();
	}
	
	// the big event handling code for all the operations handled by the program
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		String msg = src.getText().toUpperCase();
		switch(msg) {
			// user clicks 'Add' button, program switch to edit mode, so the user can edit data
			case "ADD":
				clearTextFields();
				writeLog("Adding new data");
				switchEditMode(true);
				btnAdd.setText("OK");
				btnRemove.setText("Cancel");
				btnReset.setText("Clear");
				break;
			// user clicks 'Remove' button, the selected item in the normal mode will be removed
			case "REMOVE":
				CORE_LIST.remove(list_cursor);
				if(CORE_LIST.getSize() == 0)
					displayItem(null);
				else
					displayItem((CORE_LIST.previous(list_cursor) == null ? CORE_LIST.findKth(list_cursor) : CORE_LIST.previous(list_cursor--)));
				writeLog("1 item removed");
				checkCursor();
				break;
			// user clicks 'OK' button, program will collect data, and make a ListHouse object and put it into the list
			case "OK":
				try {
					CORE_LIST.insert(getItem());
					list_cursor = CORE_LIST.getSize() - 1;
					displayItem(CORE_LIST.findKth(list_cursor));
					//displayItem(CORE_LIST.findKth(list_cursor = 0));
					writeLog("New data added");
					switchEditMode(false);
					btnAdd.setText("Add");
					btnRemove.setText("Remove");
					btnReset.setText("Reset");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Add", JOptionPane.WARNING_MESSAGE);
					txtLotNo.requestFocus();
				}
				break;
			// user clicks 'Cancel' button, user cancel adding new house data, program switch back to normal mode
			case "CANCEL":
				clearTextFields();
				displayItem(CORE_LIST.findKth(list_cursor));
				writeLog("Adding cancelled");
				switchEditMode(false);
				btnAdd.setText("Add");
				btnRemove.setText("Remove");
				btnReset.setText("Reset");
				break;
			// user clicks 'Clear' button, all text fields will be cleared, program WILL NOT switch back to normal mode
			case "CLEAR":
				clearTextFields();
				writeLog("Text-fields cleared");
				break;
			// user clicks 'Reset' button, all the elements in the list will be deleted.
			case "RESET":
				CORE_LIST.makeEmpty();
				clearTextFields();
				list_cursor = 0;
				checkCursor();
				break;
			// user clicks 'Previous' button, program will show the previous element in the list related to 'list_cursor'
			case "PREVIOUS":
				displayItem(CORE_LIST.previous(list_cursor--));
				//displayItem(CORE_LIST.findKth(--list_cursor));
				checkCursor();
				writeLog("Previous item displayed");
				break;
			// user clicks 'Next' button, program will show the next element in the list related to 'list_cursor'
			case "NEXT":
				displayItem(CORE_LIST.next(list_cursor++));
				//displayItem(CORE_LIST.findKth(++list_cursor));
				checkCursor();
				writeLog("Next item displayed");
				break;
			// user clicks 'Save' button, all the elements currently in the list will be saved to a one file
			case "SAVE":
				try {
					HouseFile.saveHouseData(CORE_LIST.printList());
					writeLog("All data saved");
				} catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage());}
				break;
			// user clicks 'Load' button, previously written data will be loaded to the program and will put them back to the list as 'ListHouse' objects
			case "LOAD":
				try {
					ListHouse[] fData = HouseFile.loadHouseData();
					for(ListHouse obj : fData) {
						CORE_LIST.insert(obj);
					}
					displayItem(CORE_LIST.findKth(list_cursor = 0));
					checkCursor();
					writeLog("All data loaded");
				} catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage());}
				break;
			// user clicks 'Find' button, a window will be displayed for finding list elements
			case "FIND":
				new RealEstateFind(CORE_LIST);
				break;
		}
	}
}
