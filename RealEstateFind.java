import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

// This class creates a window, which facilitates searching through the list
class RealEstateFind extends JFrame {
	// we need 'SortedList' object from the main program to be passed to this JFrame as an argument
	private SortedList houseList = null;
	/* this array will  keep 'ListHouse' objects as they exist in the list, so this will ease too many method calls in
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
		chkSort.addItemListener(new ItemListener() {
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
					if(findIdx >= 0) {
						addDataTable(physicalArray[findIdx]);
						btnClear.setEnabled(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Not found", "Find", JOptionPane.INFORMATION_MESSAGE);
						txtQuery.setText(null);
						txtQuery.requestFocus();
					}
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid data or empty query", "Find", JOptionPane.WARNING_MESSAGE);
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
