/* RealEstate Class
 *
 * Created By: Hashan Chandika with CodeRipperZ
 *
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RealEstate extends JFrame implements ActionListener {
	/* All the controls in the JFrame are added to its 'ContentPane', which is layed
	 * out according to 'BoxLayout'. Controls are grouped in JPanels considering
	 * their behaviour. JPanels obtain GridLayout.
	*
	// panLog contains lblLog, which is a JLabel that program writes its messages
	private JPanel panLog = new JPanel();
	// panOperations contains controls related to List operations.(Add, Remove, Reset etc)
	private JPanel panOperations = new JPanel();
	// panNavigation contains controls which helps the user to navigate through the list.(Next, Prev)
	private JPanel panNavigation = new JPanel();
	// panContent contains data-entry controls(labels and textfields)
	private JPanel panContent = new JPanel();
	
	// program writes the messages
	private JLabel lblLog = new JLabel("Message: Ready");
	
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
		
	private void initFrame() {
		// disable editing textfields
		txtLotNo.setEditable(false);
		txtFName.setEditable(false);
		txtLName.setEditable(false);
		txtPrice.setEditable(false);
		txtSqrF.setEditable(false);
		txtBedNo.setEditable(false);
		
		// set panLog layout & add its controls
		panLog.setLayout(new GridLayout(1, 1));
		panLog.add(lblLog);
		
		// set panOperations layout & add its controls
		panOperations.setLayout(new GridLayout(2, 2, 5, 3));
		panOperations.add(btnAdd);
		panOperations.add(btnRemove);
		panOperations.add(btnReset);
		panOperations.add(btnFind);
		
		// set panNavigation layout & add its controls
		panNavigation.setLayout(new GridLayout(2, 2, 5, 3));
		panNavigation.add(btnSave);
		panNavigation.add(btnLoad);
		panNavigation.add(btnPrev);
		panNavigation.add(btnNext);
		
		//	align label text to the right
		lblLotNo.setHorizontalAlignment(JLabel.RIGHT);
		lblFName.setHorizontalAlignment(JLabel.RIGHT);
		lblLName.setHorizontalAlignment(JLabel.RIGHT);
		lblPrice.setHorizontalAlignment(JLabel.RIGHT);
		lblSqrF.setHorizontalAlignment(JLabel.RIGHT);
		lblBedNo.setHorizontalAlignment(JLabel.RIGHT);
		
		// set panContent layout & add its controls
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
		
		// registring action listeners for the buttons
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnReset.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		
		// add panels to the JFrame
		add(panNavigation);
		add(panContent);
		add(panOperations);
		add(panLog);
	}
	
	public RealEstate() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));	// set frame layout
		initFrame();						// inialize frame contents
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
	
	// ### Event Handling
	
	// write log messages
	private void writeLog(String txt) {
		lblLog.setText(txt);
	}
	
	// clear all textfields
	private void clearTextFields() {
		txtLotNo.setText(null);
		txtFName.setText(null);
		txtLName.setText(null);
		txtPrice.setText(null);
		txtSqrF.setText(null);
		txtBedNo.setText(null);
	}
	
	// toggle between editmode
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
	}
	
	// event handler for buttons
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton)e.getSource();
		switch(src.getText()) {
			case "Add":
				clearTextFields();
				writeLog("Message: Adding new data");
				switchEditMode(true);
				btnAdd.setText("OK");
				btnRemove.setText("Cancel");
				btnReset.setText("Clear");
				break;
			case "Remove":
				break;
			case "OK":
				writeLog("Message: New data added");
				switchEditMode(false);
				btnAdd.setText("Add");
				btnRemove.setText("Remove");
				btnReset.setText("Reset");
				break;
			case "Cancel":
				clearTextFields();
				writeLog("Message: Adding cancelled");
				switchEditMode(false);
				btnAdd.setText("Add");
				btnRemove.setText("Remove");
				btnReset.setText("Reset");
				break;
			case "Clear":
				clearTextFields();
				writeLog("Message: Text-fields cleared");
				break;
			case "Reset":
				writeLog("Message: List emptied");
				break;
			case "Previous":
				writeLog("Message: Previous item displayed");
				break;
			case "Next":
				writeLog("Message: Next item displayed");
				break;
		}
	}
}
