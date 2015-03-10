/* RealEstate Class
 *
 * Created By: Hashan Chandika with CodeRipperZ
 *
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
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
	
	private int listPointer = 0;			// to navigate through the list
	private SortedList reList = null;		// List ADT to store 'ListHouse' objects
	
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
	
	// initializes listPointer & reList to used in the program
	private void initData() {
		reList = new SortedList(10);
		switchEditMode(false);
	}
	
	// constructor constructs the JFrame
	public RealEstate() {
		initFrame();						// initialize frame contents
		initData();						// initialize data
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	// set frame layout
		setTitle("Real Estate Program");	// set frame title
		setSize(300, 420);					// set frame size
		setResizable(false);				// disable resizing the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);									// set default exit behaviour
		setLocationRelativeTo(null);													// set frame location
		setVisible(true);					// display frame
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
	private void checkPointer() {
		btnPrev.setEnabled((reList.findKth(listPointer - 1) == null) ? false : true);
		btnNext.setEnabled((reList.findKth(listPointer + 1) == null) ? false : true);
	}
	
	// collects user entered data from textfields and returns a ListHouse object, so it can be easily add to the sorted list
	private ListHouse getItem() {
		ListHouse temp = new ListHouse();
		try {
			temp.setLotNumber(Long.parseLong(txtLotNo.getText()));
			temp.setFirstName(txtFName.getText());
			temp.setLastName(txtLName.getText());
			temp.setPrice(Double.parseDouble(txtPrice.getText()));
			temp.setSquareFeet(Float.parseFloat(txtSqrF.getText()));
			temp.setNoOfBedrooms(Integer.parseInt(txtBedNo.getText()));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
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
		switch(src.getText()) {
			// user clicks 'Add' button, program switch to edit mode, so the user can edit data
			case "Add":
				clearTextFields();
				writeLog("Adding new data");
				switchEditMode(true);
				btnAdd.setText("OK");
				btnRemove.setText("Cancel");
				btnReset.setText("Clear");
				break;
			// user clicks 'Remove' button, the selected item in the normal mode will be removed
			// not yet done <<<<<
			case "Remove":
				break;
			// user clicks 'OK' button, program will collect data, and make a ListHouse object and put it into the list
			case "OK":
				reList.insert(getItem());
				displayItem(reList.findKth(listPointer = 0));
				writeLog("New data added");
				switchEditMode(false);
				btnAdd.setText("Add");
				btnRemove.setText("Remove");
				btnReset.setText("Reset");
				break;
			// user clicks 'Cancel' button, user cancel adding new house data, program switch back to normal mode
			case "Cancel":
				clearTextFields();
				displayItem(reList.findKth(listPointer));
				writeLog("Adding cancelled");
				switchEditMode(false);
				btnAdd.setText("Add");
				btnRemove.setText("Remove");
				btnReset.setText("Reset");
				break;
			// user clicks 'Clear' button, all text fields will be cleared, program WILL NOT switch back to normal mode
			case "Clear":
				clearTextFields();
				writeLog("Text-fields cleared");
				break;
			// user clicks 'Reset' button, all the elements in the list will be deleted.
			case "Reset":
				reList.makeEmpty();
				clearTextFields();
				listPointer = 0;
				checkPointer();
				writeLog("All items removed");
				break;
			// user clicks 'Previous' button, program will show the previous element in the list related to 'listPointer'
			case "Previous":
				displayItem(reList.findKth(--listPointer));
				checkPointer();
				writeLog(String.format("Previous item displayed", listPointer + 1, reList.size()));
				break;
			// user clicks 'Next' button, program will show the next element in the list related to 'listPointer'
			case "Next":
				displayItem(reList.findKth(++listPointer));
				checkPointer();
				writeLog(String.format("Next item displayed", listPointer + 1, reList.size()));
				break;
			// user clicks 'Save' button, all the elements currently in the list will be saved to a one file
			case "Save":
				try {
					HouseFile.saveHouseData(reList.printList());
					writeLog("All data saved");
				} catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage());}
				break;
			// user clicks 'Load' button, previously written data will be loaded to the program and will put them back to the list as 'ListHouse' objects
			case "Load":
				// suhani's part
				break;
			// user clicks 'Find' button, a window will be displayed for finding list elements
			case "Find":
				new RealEstateFind(reList);
				break;
		}
	}
}

// This class creates a window, which facilitates searching through the list
class RealEstateFind extends JFrame {
	// we need 'SortedList' object from the main program to be passed to this JFrame as an argument
	private SortedList houseList = null;
	/* this array will  keep 'ListHouse' objects as they exist in the list, so this will ease to many method calls in
	 * this frame to 'printList()'
	*/
	private ListHouse[] physicalArray = null;
	
	private Container panNorth = new Container();
	private Container panCenter = new Container();
	private Container panStatus = new Container();
	
	private JLabel lbl1 = new JLabel("Enter lot number:");
	private JTextField txtQuery = new JTextField();
	private JButton btnFind = new JButton("Find");
	private JButton btnClear = new JButton("Clear");
	
	// we use a check box to trigger sort
	private JCheckBox chkSort = new JCheckBox("sort by lot number");
	private JScrollPane scrollTable = new JScrollPane();
	private JLabel lblTable = new JLabel();
	
	// combo box to select sorting order, ascending or descending
	private JComboBox<String> cmbOrder = new JComboBox<String>();
	
	// html for displaying list elements in a tabular fashion
	private final String htmlTable = "<html><table border=\"1\"><tr><th>LotNumber</th><th>Name</th><th>Price</th><th>Area</th><th>Rooms</th></tr>%s</table></html>";
	
	// inialize JFrame controls
	private void initControls() {
		btnClear.setEnabled(false);
		lbl1.setHorizontalAlignment(JLabel.RIGHT);
		
		// combo box
		cmbOrder.addItem("Ascending Order");
		cmbOrder.addItem("Descending Order");
		
		cmbOrder.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if(chkSort.isSelected() == true) {
					if(cmbOrder.getSelectedIndex() == 0)
						addDataTable(houseList.sort(SortedList.ORDER_ASCENDING));
					if(cmbOrder.getSelectedIndex() == 1)
						addDataTable(houseList.sort(SortedList.ORDER_DESCENDING));
				}
			}
		});

		// checkbox
		chkSort.addActionListener(new ActionListener() {
			public void itemStateChanged(ItemEvent evt) {
				int state = evt.getStateChange();
				if(state == ItemEvent.SELECTED) {
					if(cmbOrder.getSelectedIndex() == 0)
						addDataTable(houseList.sort(SortedList.ORDER_ASCENDING));
					if(cmbOrder.getSelectedIndex() == 1)
						addDataTable(houseList.sort(SortedList.ORDER_DESCENDING));
				}
				if(state == ItemEvent.DESELECTED)
					addDataTable(physicalArray);
			}
		});
		
		// find button
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					ListHouse obj = new ListHouse();
					obj.setLotNumber(Long.parseLong(txtQuery.getText()));
					int findIdx = houseList.find(obj);
					if(findIdx != -1) {
						addDataTable(physicalArray[findIdx]);
						btnClear.setEnabled(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Not Found", "Find", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid Data", "Find", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtQuery.setText(null);
				addDataTable(physicalArray);
				btnClear.setEnabled(false);
				chkSort.setSelected(false);
				txtQuery.requestFocus();
			}
		});
		
		// lblTable
		lblTable.setHorizontalAlignment(JLabel.CENTER);
		lblTable.setVerticalAlignment(JLabel.TOP);
		
		// scroll pane
		scrollTable.setBorder(new BevelBorder(BevelBorder.LOWERED));
		scrollTable.add(lblTable);
		scrollTable.setViewportView(lblTable);
		
		// north container
		panNorth.setLayout(new GridLayout(1, 4, 5, 0));
		panNorth.add(lbl1);
		panNorth.add(txtQuery);
		panNorth.add(btnFind);
		panNorth.add(btnClear);
		
		// center container
		panCenter.setLayout(new BorderLayout());
		panCenter.add(scrollTable, BorderLayout.CENTER);
		
		// south container
		panStatus.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panStatus.add(chkSort);
		panStatus.add(cmbOrder);
		
		// add to the frame
		add(panNorth, BorderLayout.NORTH);
		add(panCenter, BorderLayout.CENTER);
		add(panStatus, BorderLayout.SOUTH);
	}
	
	// constructor
	public RealEstateFind(SortedList list) {
		this.houseList = list;
		this.physicalArray = list.printList();
		
		initControls();
		addDataTable(physicalArray);
		setSize(500, 400);
		setTitle("Find Real Estate Data");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);		
		setVisible(true);
	}
	
	// add an array of 'ListHouse' objects to the html table
	private void addDataTable(ListHouse[] array) {
		String str = "";
		lblTable.setText(htmlTable);
		for(ListHouse obj : array) {
			str += "<tr>";
			str += String.format("<td>%d</td>", obj.getLotNumber());
			str += String.format("<td>%s, %s</td>", obj.getLastName(), obj.getFirstName());
			str += String.format("<td>%.2f</td>", obj.getPrice());
			str += String.format("<td>%.2f</td>", obj.getSquareFeet());
			str += String.format("<td>%d</td>", obj.getNoOfBedrooms());
			str += "</tr>";
		}
		lblTable.setText(String.format(lblTable.getText(), str));
	}
	
	// add one 'ListHouse' object to the html table
	private void addDataTable(ListHouse obj) {
		String str = "";
		lblTable.setText(htmlTable);
		str += "<tr>";
		str += String.format("<td>%d</td>", obj.getLotNumber());
		str += String.format("<td>%s, %s</td>", obj.getLastName(), obj.getFirstName());
		str += String.format("<td>%.2f</td>", obj.getPrice());
		str += String.format("<td>%.2f</td>", obj.getSquareFeet());
		str += String.format("<td>%d</td>", obj.getNoOfBedrooms());
		str += "</tr>";
		lblTable.setText(String.format(lblTable.getText(), str));
	}
}
