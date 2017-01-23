
/**
 * Author: Sujan Shrestha
 * Contact: snoop_suzan@yahoo.com (If you want to work and improve on this project, shoot me an email)
 * This class  is for the adding, deleting, viewing, and updating the database
 * Last modified: 12-01-2016
 * 
 * Note: 0000-00-00 is not a valid date in java while it is valid in SQL, hence the equivalence for (null or 0000-00-00 in SQL) is 0002-11-30 in java
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.mysql.jdbc.CallableStatement;

public class Database_Window {

	private static JPanel HomePanel = null;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static int xSize = ((int) tk.getScreenSize().getWidth());
	private static int ySize = ((int) tk.getScreenSize().getHeight());
	private static JComboBox table = null;
	private static String selectedItem = null;
	private static String varSQL1 = null;

	private static Container contentPane = null;

	private static JButton back = new JButton("Back");
	private static JButton Home = new JButton("Home");
	private static String itemID = null;
	private static char error_msg = '0';
	private static java.sql.CallableStatement stmt1 = null;
	private static Object[] rowdata = null;

	private static JTextField T1 = null;
	private static JTextField T2 = null;
	private static JTextField T3 = null;
	private static JTextField T4 = null;
	private static JTextField T5 = null;
	private static JTextField T6 = null;
	private static JTextField T7 = null;

	private static JLabel A1 = null;
	private static JTable viewTable = null;

	private static String[] option = { "(Select Option)", "0 - NO", "1 - YES" };
	private static JComboBox Combo = null;

	private static String[] reportViews = { "ComputerUserReport", "AddressComputerReport",
			"AccountComputerPCUserReport", "BrandPCReport", "BrandIMacReport", "BrandLaptopReport", "PCUserReport",
			"IMacUserReport", "LaptopUserReport", "AddressComputerUserReport", "AddressUserReport",
			"AccountAddressComputerPCReport", "AccountAddressPCUserReport", "AccountPCUserReport",
			"AddressComputerPCReport", "AddressPCReport", "AddressComputerPCUserReport", "AddressComputerIMacReport",
			"AddressIMacReport", "AddressComputerIMacUserReport", "AddressComputerLaptopReport", "AddressLaptopReport",
			"AddressComputerLaptopUserReport", "ComputerPCReport", "ComputerIMacReport", "ComputerLaptopReport",
			"Role_TypeUserReport", "AccountComputerPCReport", "AccountPCReport", "BrandModelReport", "IMacModelReport",
			"ModelPCReport", "LaptopModelReport", "AccountAddressComputerPCUserReport", "OperatingSystemPCReport",
			"LaptopOperatingSystemReport", "IMacOperatingSystemReport", "AccountAddressComputerUserReport",
			"AccountAddressComputerPCUserReport", "BrandOperatingSystemReport", "BrandModelPCReport" };

	public Database_Window() {
		// Calling the Home method
		Home();

	}

	private void Home() {

		HomeFrame();

		// JPanel
		HomePanel = new JPanel();
		HomePanel.setLayout(new GridLayout(9, 5));
		HomePanel.setOpaque(false);

		JLabel mainMenu = new JLabel("Main Menu", SwingConstants.CENTER);
		mainMenu.setForeground(Color.BLUE);
		mainMenu.setBackground(Color.LIGHT_GRAY);
		mainMenu.setOpaque(true);

		JButton view = new JButton("View");
		JButton Report = new JButton("Report");
		JButton exit = new JButton("Exit");
		JButton Help = new JButton("Help");

		mainMenu.setFont(new Font(view.getName(), Font.BOLD, 60));
		view.setFont(new Font(view.getName(), Font.BOLD, 30));
		Report.setFont(new Font(Report.getName(), Font.BOLD, 30));
		exit.setFont(new Font(exit.getName(), Font.BOLD, 30));
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		exit.addActionListener(new ExitListener());
		view.addActionListener(new ButtonListener());
		Report.addActionListener(new ReportListener());
		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"This is the Main page of this Program.\n"
								+ "Click on View to View/Add/Delete/Update the parts of this UNKCOEDatabase\n"
								+ "Click on Report to Generate a Report\n" + "Click on Exit to Exit this Database",
						"Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});

		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(mainMenu);
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(view);
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(Report);
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(exit);
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));
		HomePanel.add(Help);
		HomePanel.add(new JLabel(""));
		HomePanel.add(new JLabel(""));

		// Adding the HomePanel to JFrame
		Welcome.frame.add(HomePanel);
		Welcome.frame.pack();

	}

	private class ReportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			HomeFrame();

			contentPane = Welcome.frame.getContentPane();
			contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, xSize / 2, ySize / 9));

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new FlowLayout());
			mainPanel.setOpaque(false);

			JCheckBox Account = new JCheckBox("Account");
			JCheckBox Address = new JCheckBox("Address");
			JCheckBox Computer = new JCheckBox("Computer");
			JCheckBox User = new JCheckBox("User");
			JCheckBox PC = new JCheckBox("PC");
			JCheckBox IMac = new JCheckBox("IMac");
			JCheckBox Laptop = new JCheckBox("Laptop");
			JCheckBox Role_Type = new JCheckBox("Role_Type");
			JCheckBox Model = new JCheckBox("Model");
			JCheckBox OperatingSystem = new JCheckBox("OperatingSystem");
			JCheckBox Brand = new JCheckBox("Brand");
			JButton OK = new JButton("OK");
			JButton back = new JButton("Back");
			JButton Home = new JButton("Home");
			JButton Help = new JButton("Help");

			ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
			checkBoxes.add(Account);
			checkBoxes.add(Address);
			checkBoxes.add(Computer);
			checkBoxes.add(User);
			checkBoxes.add(PC);
			checkBoxes.add(IMac);
			checkBoxes.add(Laptop);
			checkBoxes.add(Role_Type);
			checkBoxes.add(Model);
			checkBoxes.add(OperatingSystem);
			checkBoxes.add(Brand);

			Account.setFont(new Font(Account.getName(), Font.BOLD, 30));
			Account.setForeground(Color.BLUE);
			Account.setBackground(Color.LIGHT_GRAY);
			Address.setFont(new Font(Address.getName(), Font.BOLD, 30));
			Address.setForeground(Color.BLUE);
			Address.setBackground(Color.LIGHT_GRAY);
			Computer.setFont(new Font(Computer.getName(), Font.BOLD, 30));
			Computer.setForeground(Color.BLUE);
			Computer.setBackground(Color.LIGHT_GRAY);
			User.setFont(new Font(User.getName(), Font.BOLD, 30));
			User.setForeground(Color.BLUE);
			User.setBackground(Color.LIGHT_GRAY);
			PC.setFont(new Font(PC.getName(), Font.BOLD, 30));
			PC.setForeground(Color.BLUE);
			PC.setBackground(Color.LIGHT_GRAY);
			IMac.setFont(new Font(IMac.getName(), Font.BOLD, 30));
			IMac.setForeground(Color.BLUE);
			IMac.setBackground(Color.LIGHT_GRAY);
			Laptop.setFont(new Font(Laptop.getName(), Font.BOLD, 30));
			Laptop.setForeground(Color.BLUE);
			Laptop.setBackground(Color.LIGHT_GRAY);
			Role_Type.setFont(new Font(Role_Type.getName(), Font.BOLD, 30));
			Role_Type.setForeground(Color.BLUE);
			Role_Type.setBackground(Color.LIGHT_GRAY);
			Model.setFont(new Font(Model.getName(), Font.BOLD, 30));
			Model.setForeground(Color.BLUE);
			Model.setBackground(Color.LIGHT_GRAY);
			OperatingSystem.setFont(new Font(OperatingSystem.getName(), Font.BOLD, 30));
			OperatingSystem.setForeground(Color.BLUE);
			OperatingSystem.setBackground(Color.LIGHT_GRAY);
			Brand.setFont(new Font(Brand.getName(), Font.BOLD, 30));
			Brand.setForeground(Color.BLUE);
			Brand.setBackground(Color.LIGHT_GRAY);
			OK.setFont(new Font(OK.getName(), Font.BOLD, 30));
			OK.setForeground(Color.BLUE);
			back.setFont(new Font(back.getName(), Font.BOLD, 30));
			back.setForeground(Color.BLUE);
			Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
			Home.setForeground(Color.BLUE);
			Help.setFont(new Font(Help.getName(), Font.BOLD, 30));
			Help.setForeground(Color.BLUE);

			Home.addActionListener(new HomeButtonListener());
			back.addActionListener(new HomeButtonListener());

			JPanel panel1 = new JPanel();
			panel1.setOpaque(false);
			panel1.setLayout(new GridLayout(9, 5));

			panel1.add(Computer);
			panel1.add(new JLabel(""));
			panel1.add(User);
			panel1.add(new JLabel(""));
			panel1.add(Address);
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(Account);
			panel1.add(new JLabel(""));
			// panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));
			panel1.add(PC);
			panel1.add(new JLabel(""));
			panel1.add(IMac);
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(Laptop);
			panel1.add(new JLabel(""));
			panel1.add(Role_Type);
			panel1.add(new JLabel(""));
			// panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));panel1.add(new
			// JLabel(""));panel1.add(new JLabel(""));
			panel1.add(Model);
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(OperatingSystem);
			panel1.add(new JLabel(""));
			panel1.add(Brand);
			panel1.add(new JLabel(""));
			panel1.add(Help);

			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(new JLabel(""));
			panel1.add(back);
			panel1.add(new JLabel(""));
			panel1.add(OK);
			panel1.add(new JLabel(""));
			panel1.add(Home);

			Dimension d = new Dimension((int) (xSize / 1.2), (int) (ySize / 1.2));
			panel1.setPreferredSize(d);
			panel1.setOpaque(false);
			mainPanel.add(panel1);
			mainPanel.setPreferredSize(d);
			mainPanel.setOpaque(false);

			Welcome.frame.add(mainPanel);
			Welcome.frame.pack();

			Help.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null,
							"You are now viewing the Generate Report Page of the UNKCOEDatabase.\n"
									+ "You can click on multiple Checkboxes to generate the Report from UNKCOEDatabase\n"
									+ "You can click on Home to roll back to the home or main page of the UNKCOEDatabase\n"
									+ "You can click on Back to roll back to the previous page of the UNKCOEDatabase",
							"Help!", JOptionPane.QUESTION_MESSAGE);
				}

			});

			OK.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					boolean pass = false;
					// TODO Auto-generated method stub
					for (JCheckBox checkBox : checkBoxes) {
						if (checkBox.isSelected()) {
							pass = true;
						}
					}
					if (pass == true) {
						List<String> infos = new ArrayList<String>();
						for (JCheckBox checkBox : checkBoxes) {
							if (checkBox.isSelected()) {
								infos.add(checkBox.getText());
							}
						}
						Collections.sort(infos);
						String table = "";
						for (String text : infos) {
							table = table + text;
						}
						boolean match = false;
						for (String report : reportViews) {
							if ((table + "Report").equals(report)) {
								match = true;
								break;
							}
						}

						if (match == true) {
							GenerateReport(table);
						} else {
							JOptionPane.showMessageDialog(null,
									"The table UNKCOEInventory." + table + "Report doesn't exist!\n"
											+ "Please select a different JCheckbox combination",
									"Error!!", JOptionPane.ERROR_MESSAGE);
							for (JCheckBox checkBox : checkBoxes) {
								if (checkBox.isSelected()) {
									checkBox.setSelected(false);
								}
							}
						}
					}

					else {
						JOptionPane.showMessageDialog(null, "Please select a valid combination JCheckbox", "Error!!",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

	private void GenerateReport(String table) {

		HomeFrame();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setOpaque(false);

		JButton search = new JButton("Search");
		JButton back = new JButton("Back");
		JButton Help = new JButton("Help");

		JPanel sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		sidePanel.setLayout(new GridLayout(7, 1));

		sidePanel.add(search);
		sidePanel.add(new JLabel(""));
		sidePanel.add(back);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Home);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Help);

		search.setFont(new Font(search.getName(), Font.BOLD, 30));
		Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
		Home.addActionListener(new HomeButtonListener());
		back.setFont(new Font(back.getName(), Font.BOLD, 30));
		back.addActionListener(new ReportListener());
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"You are now viewing the Report of the UNKCOEDatabase.\n"
								+ "You can click on Search to search a specific information in the Report of UNKCOEDatabase\n"
								+ "You can click on Home to roll back to the home or main page of the UNKCOEDatabase",
						"Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String search = JOptionPane.showInputDialog("Enter the ID:  ", "SearchBox");

				int rows = viewTable.getRowCount();
				int columns = viewTable.getColumnCount();
				if (search != null) {
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < columns; j++) {
							Object value = viewTable.getValueAt(i, j);
							if (value != null && value.toString().matches(".*" + Pattern.quote(search) + ".*")) {
								// this will automatically set the view of the
								// scroll in the location of the value
								viewTable.scrollRectToVisible(viewTable.getCellRect(i, 0, true));

								// this will automatically set the focus of the
								// searched/selected row/value
								viewTable.setRowSelectionInterval(i, i);

								for (int k = 0; k <= viewTable.getColumnCount() - 1; k++) {

									viewTable.getColumnModel().getColumn(k).setCellRenderer(new HighlightRenderer());
								}
							}
						}
					}
				}
			}
		});

		int row = 0;
		try {
			Statement stmt1 = Welcome.conn.createStatement();
			String varSQL1 = "SELECT COUNT(*) FROM " + table + "Report";
			ResultSet rs = stmt1.executeQuery(varSQL1);
			rs.next();
			row = rs.getInt(1);
			rs.close();
			stmt1.close();
		} catch (SQLException ex) {

		}

		try {
			Statement stmt = Welcome.conn.createStatement();
			String varSQL = "Select * FROM " + table + "Report";
			ResultSet rs = stmt.executeQuery(varSQL);
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columns = rsMeta.getColumnCount();
			String[][] rowData = new String[row][columns];
			String[] columnNames = new String[columns];
			String Name = "";
			for (int c = 1; c <= columns; c++) {
				Name += rsMeta.getColumnName(c) + " ";
				columnNames[c - 1] = Name;

				Name = "";
			}
			int b = 0;
			while (rs.next()) {
				for (int c = 1; c <= columns; c++) {
					Name += rs.getString(c) + " ";
					rowData[b][c - 1] = Name;
					Name = "";
				}
				b++;
			}
			viewTable = new JTable(rowData, columnNames) {
				@Override

				// Changing the width of the table cells to fit its contents
				// inside the cell
				// Getting rid of the truncated text inside the Table cell
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					Component component = super.prepareRenderer(renderer, row, column);
					int rendererWidth = component.getPreferredSize().width;
					TableColumn tableColumn = getColumnModel().getColumn(column);
					tableColumn.setPreferredWidth(
							Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
					return component;
				}
			};
			viewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			// Setting the table font
			viewTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 25));
			viewTable.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			// Setting the table row height to 40 pixels
			viewTable.setRowHeight(40);

			Dimension d = new Dimension((int) (xSize / 1.5), (int) (ySize / 1.5));
			// Adding a JScrollPane to the JPanel for allowing the scrollability
			JScrollPane jScrollpanel = new JScrollPane(viewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollpanel.setPreferredSize(d);
			// viewTable.setPreferredSize(d);

			for (int j = 0; j < columns; j++) {
				viewTable.getColumnModel().getColumn(j).setPreferredWidth(300);

			}
			mainPanel.add(jScrollpanel);
			mainPanel.add(sidePanel);
			Welcome.frame.add(mainPanel, BorderLayout.CENTER);
			Welcome.frame.revalidate();
			Welcome.frame.repaint();
			Welcome.frame.pack();
			rs.close();
			stmt.close();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "GoodBye!");
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent b) {

			// Setting everything back to null to restart

			table = null;
			viewTable = null;
			selectedItem = null;
			contentPane = null;
			stmt1 = null;
			varSQL1 = null;
			itemID = null;
			error_msg = 0;
			T1 = T2 = T3 = T4 = T5 = T6 = T7 = null;

			// Calling the HomeFrame method

			HomeFrame();
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(1, 3));

			JButton Help = new JButton("Help");
			Help.setFont(new Font(Help.getName(), Font.BOLD, 30));
			Help.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null,
							"You have selected the view page.\n"
									+ "Choose the desired option from the JComboBox to view the record from this UNKCOEDatabase",
							"Help!", JOptionPane.QUESTION_MESSAGE);
				}

			});

			JPanel sidePanel = new JPanel();
			sidePanel.setLayout(new GridLayout(5, 1));
			sidePanel.setOpaque(false);
			sidePanel.add(back);
			sidePanel.add(new JLabel(""));
			sidePanel.add(Home);
			sidePanel.add(new JLabel(""));
			sidePanel.add(Help);

			back.setFont(new Font(back.getName(), Font.BOLD, 30));
			back.addActionListener(new HomeButtonListener());
			Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
			Home.addActionListener(new HomeButtonListener());
			Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

			// Table JPanel
			JPanel tablePanel = new JPanel(new GridLayout(1, 1));
			String[] tables = { "(Select Option)", "Address", "Computer", "Account", "PC", "IMac", "Laptop",
					"OperatingSystem", "Model", "Model_has_OperatingSystem", "Brand", "User", "Role_Type" };
			table = new JComboBox(tables);
			table.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					JComboBox comb = (JComboBox) e.getSource();
					String newChoice = (String) comb.getSelectedItem();
					if (newChoice != "(Select Option)") {
						switch (newChoice) {
						case "Address": {
							A1 = new JLabel("Room_Num:(Str)  ");
							break;
						}
						case "Computer": {
							A1 = new JLabel("ComputerName");
							break;
						}
						case "Account": {
							A1 = new JLabel("AccountName");
							break;
						}
						case "PC": {
							A1 = new JLabel("ComputerName");
							break;
						}
						case "IMac": {
							A1 = new JLabel("ComputerName");
							break;
						}
						case "Laptop": {
							A1 = new JLabel("ComputerName");
							break;
						}
						case "OperatingSystem": {
							A1 = new JLabel("OS_ID:(Str)  ");
							break;
						}
						case "Model": {
							A1 = new JLabel("ModelID:(Str)  ");
							break;
						}
						case "Model_has_OperatingSystem": {
							A1 = new JLabel("ModelID:(Str)  ");
							break;
						}
						case "Brand": {
							A1 = new JLabel("BrandID:(Str)  ");
							break;
						}
						case "User": {
							A1 = new JLabel("UserID:(Int)  ");
							break;
						}
						case "Role_Type": {
							A1 = new JLabel("RoleID:(Str)  ");
							break;
						}

						}
						ViewRecord(newChoice);

					}
				}
			});
			table.setFont(new Font(table.getName(), Font.BOLD, 30));

			tablePanel.add(table);
			tablePanel.setOpaque(false);

			mainPanel.add(tablePanel);
			mainPanel.add(new JLabel(""));
			mainPanel.add(sidePanel);
			mainPanel.setOpaque(false);

			Welcome.frame.add(mainPanel);
			Welcome.frame.pack();

			selectedItem = (String) table.getSelectedItem();
		}

	}

	private class HomeButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Setting everything back to null to restart
			table = null;
			selectedItem = null;
			String varSQL1 = null;
			contentPane = null;
			stmt1 = null;
			varSQL1 = null;
			itemID = null;
			error_msg = 0;
			T1 = T2 = T3 = T4 = T5 = T6 = T7 = null;

			// Calling Home method
			Home();

		}
	}

	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Welcome.conn.close();
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
			System.exit(0);
		}
	}

	/*
	 * This method sets the Current Container in JFrame to back to the
	 * HomeJFrame
	 */
	private void HomeFrame() {
		if (Welcome.frame.getContentPane() != null) {
			Welcome.frame.getContentPane().removeAll();
			;
		}
		Welcome.frame.setVisible(true);

		ImageIcon img = new ImageIcon(this.getClass().getResource(".\\images\\fountain.jpg"));
		Image image = img.getImage(); // transform it
		Image newimg = image.getScaledInstance(xSize, ySize, java.awt.Image.SCALE_SMOOTH); // scale
		// it
		// the
		// smooth
		// way
		ImageIcon newImage = new ImageIcon(newimg);

		JLabel background = new JLabel(newImage);
		background.setLayout(new FlowLayout());
		Welcome.frame.setContentPane(background);

		contentPane = Welcome.frame.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, xSize / 2, ySize / 6));

	}

	public JPanel Address(JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("Address", SwingConstants.CENTER);
		A1 = new JLabel("Room_Num:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("BuildingName", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("Department", SwingConstants.CENTER);
		T3 = new JTextField();
		JLabel A4 = new JLabel("AddressDescription ", SwingConstants.CENTER);
		T4 = new JTextField();

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		A1.setForeground(Color.BLUE);
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		A2.setForeground(Color.BLUE);
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		A3.setForeground(Color.BLUE);
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		A4.setForeground(Color.BLUE);
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel AddressUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("Address", SwingConstants.CENTER);
		A1 = new JLabel("Room_Num:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]));
		JLabel A2 = new JLabel("BuildingName", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]));
		JLabel A3 = new JLabel("Department", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]));
		JLabel A4 = new JLabel("AddressDescription ", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]), 15);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		A1.setForeground(Color.BLUE);
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		A2.setForeground(Color.BLUE);
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		A3.setForeground(Color.BLUE);
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		A4.setForeground(Color.BLUE);
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel AddressDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(5, 3));

		JLabel ALabel = new JLabel("Address", SwingConstants.CENTER);
		A1 = new JLabel("Room_Num:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);
		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		A1.setForeground(Color.BLUE);
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Account(JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(11, 3));
		JLabel ALabel = new JLabel("Account", SwingConstants.CENTER);
		A1 = new JLabel("AccountName", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("Password", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("IsActive", SwingConstants.CENTER);
		Combo = (new JComboBox(option));
		Combo.setFont(new Font(Combo.getName(), Font.BOLD, 30));
		T3 = new JTextField();
		Combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) Combo.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(((String) Combo.getSelectedItem()).substring(0, 1));
				} else {
					T3 = new JTextField("0");
				}
			}

		});

		JLabel A4 = new JLabel("ComputerName", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("PC"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));
		T4 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T4 = new JTextField((String) combo.getSelectedItem());
				} else {
					T4 = new JTextField("Unspecified");
				}
			}

		});

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(Combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());

		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel AccountUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(11, 3));
		JLabel ALabel = new JLabel("Account", SwingConstants.CENTER);
		A1 = new JLabel("AccountName", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("Password", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("IsActive", SwingConstants.CENTER);
		T3 = new JTextField((((String) rowdata[2]).trim()));
		JLabel A4 = new JLabel("ComputerName", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());

		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel AccountDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("Account", SwingConstants.CENTER);
		A1 = new JLabel("AccountName", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Computer(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(17, 3));
		JLabel ALabel = new JLabel("Computer", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("UserID:", SwingConstants.CENTER);
		JComboBox combo1 = new JComboBox(Return_ID("User"));
		combo1.setFont(new Font(combo1.getName(), Font.BOLD, 30));

		T2 = new JTextField();
		combo1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo1.getSelectedItem() != ("(Select Option)")) {
					T2 = new JTextField(combo1.getSelectedItem().toString());
				} else {
					T2 = new JTextField("0");
				}
			}

		});

		JLabel A3 = new JLabel("Room_Num:", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Address"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));
		T3 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T3 = new JTextField("0");
				}
			}

		});

		JLabel A4 = new JLabel("ComputerPassword", SwingConstants.CENTER);
		T4 = new JTextField();

		JLabel A5 = new JLabel("ComputerAssignedDate", SwingConstants.CENTER);
		T5 = new JTextField("YYYY-MM-DD");
		JLabel A6 = new JLabel("ComputerReturnedDate", SwingConstants.CENTER);
		T6 = new JTextField("YYYY-MM-DD");

		JLabel A7 = new JLabel("IsUpToDate", SwingConstants.CENTER);
		Combo = (new JComboBox(option));
		Combo.setFont(new Font(Combo.getName(), Font.BOLD, 30));

		T7 = new JTextField();
		Combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) Combo.getSelectedItem() != ("(Select Option)")) {
					T7 = new JTextField(((String) Combo.getSelectedItem()).substring(0, 1));
				} else {
					T7 = new JTextField("0");
				}
			}

		});
		// T7 = new JTextField(Combo.getSelectedItem().toString().substring(0,
		// 1));

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		T5.setFont(new Font(T5.getName(), Font.BOLD, 30));
		A6.setFont(new Font(A6.getName(), Font.BOLD, 30));
		T6.setFont(new Font(T6.getName(), Font.BOLD, 30));
		A7.setFont(new Font(A7.getName(), Font.BOLD, 30));
		T7.setFont(new Font(T7.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);
		A6.setForeground(Color.BLUE);
		A7.setForeground(Color.BLUE);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);
		A6.setBackground(Color.LIGHT_GRAY);
		A6.setOpaque(true);
		A7.setBackground(Color.LIGHT_GRAY);
		A7.setOpaque(true);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(combo1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(T5);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A6);
		recordPanel.add(new JLabel());
		recordPanel.add(T6);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A7);
		recordPanel.add(new JLabel());
		recordPanel.add(Combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel ComputerUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(17, 3));
		JLabel ALabel = new JLabel("Computer", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("UserID:(Int)  ", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("Room_Num:(Str)  ", SwingConstants.CENTER);
		T3 = new JTextField((((String) rowdata[2]).trim()));
		JLabel A4 = new JLabel("ComputerPassword", SwingConstants.CENTER);
		T4 = new JTextField((((String) rowdata[3]).trim()));

		// SqlDateModel model1 = new SqlDateModel();
		// Properties p1 = new Properties();
		// p1.put("text.today", "Today");
		// p1.put("text.month", "Month");
		// p1.put("text.year", "Year");
		//
		// SqlDateModel model2 = new SqlDateModel();
		// Properties p2 = new Properties();
		// p2.put("text.today", "Today");
		// p2.put("text.month", "Month");
		// p2.put("text.year", "Year");
		//
		// JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		// JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		//
		// JLabel A5 = new JLabel("ComputerAssignedDate",
		// SwingConstants.CENTER);
		// JDatePickerImpl T5 = new JDatePickerImpl(datePanel1, new
		// DateLabelFormatter());
		// java.sql.Date selectedDate = (java.sql.Date)
		// T5.getModel().getValue();
		// // JDatePickerImpl T5 = new JDatePickerImpl(datePanel1,new
		// // DateLabelFormatter());
		// try {
		// T5 = new JDatePickerImpl(
		// new JDatePanelImpl(
		// new SqlDateModel(new Date((long) Integer.parseInt(((String)
		// rowdata[4]).trim()))), p1),
		// new DateLabelFormatter());
		// selectedDate = (java.sql.Date) T5.getModel().getValue();
		// } catch (NumberFormatException n) {
		// }
		//
		// // System.out.println(selectedDate.toString());
		//
		// JLabel A6 = new JLabel("ComputerReturnedDate",
		// SwingConstants.CENTER);
		// JDatePickerImpl T6 = new JDatePickerImpl(datePanel2, new
		// DateLabelFormatter());
		// java.sql.Date selectedDate2 = (java.sql.Date)
		// T6.getModel().getValue();
		// try {
		// // JDatePickerImpl T6 = new JDatePickerImpl(datePanel2,new
		// // DateLabelFormatter());
		// T6 = new JDatePickerImpl(
		// new JDatePanelImpl(
		// new SqlDateModel(new Date((long) Integer.parseInt(((String)
		// rowdata[5]).trim()))), p2),
		// new DateLabelFormatter());
		// selectedDate2 = (java.sql.Date) T6.getModel().getValue();
		// } catch (NumberFormatException n) {
		// }

		JLabel A5 = new JLabel("ComputerAssignedDate:", SwingConstants.CENTER);
		T5 = new JTextField(((String) rowdata[4]).trim());
		JLabel A6 = new JLabel("ComputerReturnedDate", SwingConstants.CENTER);
		T6 = new JTextField(((String) rowdata[5]).trim());
		JLabel A7 = new JLabel("IsUpToDate", SwingConstants.CENTER);
		T7 = new JTextField(((String) rowdata[6]).trim());

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		T5.setFont(new Font(T5.getName(), Font.BOLD, 30));
		A6.setFont(new Font(A6.getName(), Font.BOLD, 30));
		T6.setFont(new Font(T6.getName(), Font.BOLD, 30));
		A7.setFont(new Font(A7.getName(), Font.BOLD, 30));
		T7.setFont(new Font(T7.getName(), Font.BOLD, 30));

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);
		A6.setBackground(Color.LIGHT_GRAY);
		A6.setOpaque(true);
		A7.setBackground(Color.LIGHT_GRAY);
		A7.setOpaque(true);

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);
		A6.setForeground(Color.BLUE);
		A7.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(T5);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A6);
		recordPanel.add(new JLabel());
		recordPanel.add(T6);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A7);
		recordPanel.add(new JLabel());
		recordPanel.add(T7);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel ComputerDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("Computer", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel PC(JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(13, 3));
		JLabel ALabel = new JLabel("PC", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Computer"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T1 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T1 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T1 = new JTextField("Unspecified");
				}
			}

		});
		// T1 = new JTextField(combo.getSelectedItem().toString());

		JLabel A2 = new JLabel("Memory (Gb)", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("Storage (GB)", SwingConstants.CENTER);
		T3 = new JTextField();
		JLabel A4 = new JLabel("ModelID:", SwingConstants.CENTER);
		JComboBox combo1 = new JComboBox(Return_ID("Model"));
		combo1.setFont(new Font(combo1.getName(), Font.BOLD, 30));

		T4 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo1.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo1.getSelectedItem().toString());
				} else {
					T3 = new JTextField("Unspecified");
				}
			}

		});
		// T4 = new JTextField(combo1.getSelectedItem().toString());

		JLabel A5 = new JLabel("OS_ID:", SwingConstants.CENTER);
		JComboBox combo2 = new JComboBox(Return_ID("OperatingSytem"));
		combo2.setFont(new Font(combo2.getName(), Font.BOLD, 30));

		T5 = new JTextField();
		combo2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo2.getSelectedItem() != ("(Select Option)")) {
					T5 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T5 = new JTextField("Unspecified");
				}
			}

		});
		// T5 = new JTextField(combo2.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		T5.setFont(new Font(T5.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(combo1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(combo2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel PCUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(13, 3));
		JLabel ALabel = new JLabel("PC", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("Memory (Gb)", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("Storage (GB)", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]).trim());
		JLabel A4 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]).trim());
		JLabel A5 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T5 = new JTextField(((String) rowdata[4]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		T5.setFont(new Font(T5.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(T5);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel PCDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("PC", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Laptop(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("Laptop", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Computer"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T1 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T1 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T1 = new JTextField("Unspecified");
				}
			}

		});
		// T1 = new JTextField(combo.getSelectedItem().toString());

		JLabel A2 = new JLabel("Size(Inch)", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		JComboBox combo1 = new JComboBox(Return_ID("Model"));
		combo1.setFont(new Font(combo1.getName(), Font.BOLD, 30));

		T3 = new JTextField();
		combo1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo1.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo1.getSelectedItem().toString());
				} else {
					T3 = new JTextField("Unspecified");
				}
			}

		});
		// T3 = new JTextField(combo1.getSelectedItem().toString());

		JLabel A4 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		JComboBox combo2 = new JComboBox(Return_ID("Role_Type"));
		combo2.setFont(new Font(combo2.getName(), Font.BOLD, 30));

		T4 = new JTextField();
		combo2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo2.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo2.getSelectedItem().toString());
				} else {
					T3 = new JTextField("Unspecified");
				}
			}

		});
		// T4 = new JTextField(combo2.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(combo1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(combo2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel LaptopUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("Laptop", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("Size(Inch)", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]).trim());
		JLabel A4 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel LaptopDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));

		JLabel ALabel = new JLabel("Laptop", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel IMac(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("IMac", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Computer"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T1 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T1 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T1 = new JTextField("Unspecified");
				}
			}

		});
		// T1 = new JTextField(combo.getSelectedItem().toString());

		JLabel A2 = new JLabel("RetinaDisplay", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("ModelID:", SwingConstants.CENTER);
		JComboBox combo1 = new JComboBox(Return_ID("Model"));
		combo1.setFont(new Font(combo1.getName(), Font.BOLD, 30));

		T3 = new JTextField();
		combo1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo1.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo1.getSelectedItem().toString());
				} else {
					T3 = new JTextField("Unspecified");
				}
			}

		});
		// T3 = new JTextField(combo1.getSelectedItem().toString());

		JLabel A4 = new JLabel("OS_ID:", SwingConstants.CENTER);
		JComboBox combo2 = new JComboBox(Return_ID("OperatingSystem"));
		combo2.setFont(new Font(combo2.getName(), Font.BOLD, 30));

		T4 = new JTextField();
		combo2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo2.getSelectedItem() != ("(Select Option)")) {
					T4 = new JTextField(combo2.getSelectedItem().toString());
				} else {
					T4 = new JTextField("Unspecified");
				}
			}

		});
		// T4 = new JTextField(combo2.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(combo1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(combo2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel IMacUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(11, 3));

		JLabel ALabel = new JLabel("IMac", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("RetinaDisplay", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]).trim());
		JLabel A4 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel IMacDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));

		JLabel ALabel = new JLabel("IMac", SwingConstants.CENTER);
		A1 = new JLabel("ComputerName", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Model(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(9, 3));
		JLabel ALabel = new JLabel("Model", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("ModelDescription", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("BrandID:", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Brand"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T3 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T3 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T3 = new JTextField("Unspecified");
				}
			}

		});
		// T3 = new JTextField(combo.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel ModelUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(9, 3));
		JLabel ALabel = new JLabel("Model", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("ModelDescription", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("BrandID:(Str)  ", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel ModelDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("Model", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Model_has_OperatingSystem(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Model_has_OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Model"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T1 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T1 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T1 = new JTextField("Unspecified");
				}
			}

		});
		// T1 = new JTextField(combo.getSelectedItem().toString());

		JLabel A2 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		JComboBox combo1 = new JComboBox(Return_ID("OperatingSystem"));
		combo1.setFont(new Font(combo1.getName(), Font.BOLD, 30));
		T2 = new JTextField();
		combo1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo1.getSelectedItem() != ("(Select Option)")) {
					T2 = new JTextField(combo1.getSelectedItem().toString());
				} else {
					T2 = new JTextField("Unspecified");
				}
			}

		});

		// T2 = new JTextField(combo1.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(combo1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Model_has_OperatingSystemUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Model_has_OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Model_has_OperatingSystemDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Model_has_OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("ModelID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);
		JLabel A2 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T2 = new JTextField((String) rowdata[1]);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel User(JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(13, 3));
		JLabel ALabel = new JLabel("User", SwingConstants.CENTER);
		A1 = new JLabel("UserID:(Int)  ", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("FirstName", SwingConstants.CENTER);
		T2 = new JTextField();
		JLabel A3 = new JLabel("LastName", SwingConstants.CENTER);
		T3 = new JTextField();
		JLabel A4 = new JLabel("Contact", SwingConstants.CENTER);
		T4 = new JTextField();
		JLabel A5 = new JLabel("RoleID", SwingConstants.CENTER);
		JComboBox combo = new JComboBox(Return_ID("Role_Type"));
		combo.setFont(new Font(combo.getName(), Font.BOLD, 30));

		T5 = new JTextField();
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((String) combo.getSelectedItem() != ("(Select Option)")) {
					T5 = new JTextField(combo.getSelectedItem().toString());
				} else {
					T5 = new JTextField("Unspecified");
				}
			}

		});
		// T5 = new JTextField(combo.getSelectedItem().toString());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		// T5.setFont(new Font(T5.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(combo);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel UserUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(13, 3));
		JLabel ALabel = new JLabel("User", SwingConstants.CENTER);
		A1 = new JLabel("UserID:(Int)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("FirstName", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());
		JLabel A3 = new JLabel("LastName", SwingConstants.CENTER);
		T3 = new JTextField(((String) rowdata[2]).trim());
		JLabel A4 = new JLabel("Contact", SwingConstants.CENTER);
		T4 = new JTextField(((String) rowdata[3]).trim());
		JLabel A5 = new JLabel("RoleID:(Str)  ", SwingConstants.CENTER);
		T5 = new JTextField(((String) rowdata[4]).trim());

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);
		A3.setBackground(Color.LIGHT_GRAY);
		A3.setOpaque(true);
		A4.setBackground(Color.LIGHT_GRAY);
		A4.setOpaque(true);
		A5.setBackground(Color.LIGHT_GRAY);
		A5.setOpaque(true);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));
		A3.setFont(new Font(A3.getName(), Font.BOLD, 30));
		T3.setFont(new Font(T3.getName(), Font.BOLD, 30));
		A4.setFont(new Font(A4.getName(), Font.BOLD, 30));
		T4.setFont(new Font(T4.getName(), Font.BOLD, 30));
		A5.setFont(new Font(A5.getName(), Font.BOLD, 30));
		T5.setFont(new Font(T5.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A3.setForeground(Color.BLUE);
		A4.setForeground(Color.BLUE);
		A5.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A3);
		recordPanel.add(new JLabel());
		recordPanel.add(T3);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A4);
		recordPanel.add(new JLabel());
		recordPanel.add(T4);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A5);
		recordPanel.add(new JLabel());
		recordPanel.add(T5);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel UserDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("User", SwingConstants.CENTER);
		A1 = new JLabel("UserID:(Int)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel OperatingSystem(JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(7, 3));

		JLabel ALabel = new JLabel("OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("OS_ID", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("OS_Description", SwingConstants.CENTER);
		T2 = new JTextField();

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);
		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel OperatingSystemUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(7, 3));

		JLabel ALabel = new JLabel("OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("OS_Description:(Str)  ", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);
		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel OperatingSystemDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();
		recordPanel.setLayout(new GridLayout(5, 3));

		JLabel ALabel = new JLabel("OperatingSystem", SwingConstants.CENTER);
		A1 = new JLabel("OS_ID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Role_Type(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Role_Type", SwingConstants.CENTER);
		A1 = new JLabel("RoleID", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("RoleDescription", SwingConstants.CENTER);
		T2 = new JTextField();

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Role_TypeUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Role_Type", SwingConstants.CENTER);
		A1 = new JLabel("RoleID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("RoleDescription", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Role_TypeDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("Role_Type", SwingConstants.CENTER);
		A1 = new JLabel("RoleID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);
		ALabel.setOpaque(true);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel Brand(JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Brand", SwingConstants.CENTER);
		A1 = new JLabel("BrandID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField();
		JLabel A2 = new JLabel("BrandDescription", SwingConstants.CENTER);
		T2 = new JTextField();

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel BrandUpdate(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(7, 3));
		JLabel ALabel = new JLabel("Brand", SwingConstants.CENTER);
		A1 = new JLabel("BrandID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField(((String) rowdata[0]).trim());
		JLabel A2 = new JLabel("BrandDescription", SwingConstants.CENTER);
		T2 = new JTextField(((String) rowdata[1]).trim());

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);
		A2.setBackground(Color.LIGHT_GRAY);
		A2.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));
		A2.setFont(new Font(A2.getName(), Font.BOLD, 30));
		T2.setFont(new Font(T2.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);
		A2.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A2);
		recordPanel.add(new JLabel());
		recordPanel.add(T2);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	public JPanel BrandDelete(Object[] rowdata, JButton OKbutton) {
		JPanel recordPanel = new JPanel();

		recordPanel.setLayout(new GridLayout(5, 3));
		JLabel ALabel = new JLabel("Brand", SwingConstants.CENTER);
		A1 = new JLabel("BrandID:(Str)  ", SwingConstants.CENTER);
		T1 = new JTextField((String) rowdata[0]);

		ALabel.setFont(new Font(ALabel.getName(), Font.BOLD, 30));
		ALabel.setForeground(Color.BLUE);
		ALabel.setBackground(Color.LIGHT_GRAY);

		A1.setBackground(Color.LIGHT_GRAY);
		A1.setOpaque(true);

		A1.setFont(new Font(A1.getName(), Font.BOLD, 30));
		T1.setFont(new Font(T1.getName(), Font.BOLD, 30));

		A1.setForeground(Color.BLUE);

		recordPanel.add(new JLabel());
		recordPanel.add(ALabel);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(A1);
		recordPanel.add(new JLabel());
		recordPanel.add(T1);
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(new JLabel());
		recordPanel.add(OKbutton);
		recordPanel.add(new JLabel());

		return recordPanel;
	}

	private void ExecuteBrandQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.registerOutParameter(3, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(3));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteRole_TypeQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.registerOutParameter(3, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(3));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteOperatingSystemQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.registerOutParameter(3, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(3));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteModelQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.setString(3, T3.getText());
			stmt1.registerOutParameter(4, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(4));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteModel_has_OperatingSystemQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.registerOutParameter(3, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(3));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteUserQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setInt(1, Integer.parseInt(T1.getText().trim()));
			stmt1.setString(2, T2.getText());
			stmt1.setString(3, T3.getText());
			stmt1.setString(4, T4.getText());
			stmt1.setString(5, T5.getText());
			stmt1.registerOutParameter(6, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(6));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteLaptopQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setInt(2, Integer.parseInt(T2.getText().trim()));
			stmt1.setString(3, T3.getText());
			stmt1.setString(4, T4.getText());
			stmt1.registerOutParameter(5, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(5));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteIMacQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setInt(2, Integer.parseInt(T2.getText().trim()));
			stmt1.setString(3, T3.getText());
			stmt1.setString(4, T4.getText());
			stmt1.registerOutParameter(5, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(5));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecutePCQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setInt(2, Integer.parseInt(T2.getText().trim()));
			stmt1.setInt(3, Integer.parseInt(T3.getText().trim()));
			stmt1.setString(4, T4.getText());
			stmt1.setString(5, T5.getText());
			stmt1.registerOutParameter(6, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(6));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error!!", e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteAddressQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText().trim());
			stmt1.setString(2, T2.getText().trim());
			stmt1.setString(3, T3.getText().trim());
			stmt1.setString(4, T4.getText().trim());
			stmt1.registerOutParameter(5, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(5));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void ExecuteComputerQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			try {
				stmt1.setInt(2, Integer.parseInt(T2.getText().trim()));
			} catch (Exception e) {
			}
			stmt1.setString(3, T3.getText());
			stmt1.setString(4, T4.getText());

			// This object can interpret strings representing dates in the
			// format MM/dd/yyyy
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// Convert from String to Date
			if (T5.getText().equals("null") || T5.getText().equals("YYYY-MM-DD")) {
				T5 = new JTextField("0000-00-00");
			}
			if (T6.getText().equals("null") || T6.getText().equals("YYYY-MM-DD")) {
				T6 = new JTextField("0000-00-00");
			}
			java.util.Date startDate = df.parse((T5.getText()).trim());
			java.util.Date endDate = df.parse((T6.getText()).trim());
			stmt1.setDate(5, new java.sql.Date(startDate.getTime()));
			stmt1.setDate(6, new java.sql.Date(endDate.getTime()));
			stmt1.setInt(7, Integer.parseInt(T7.getText().trim()));
			stmt1.registerOutParameter(8, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(8));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ExecuteAccountQuery(String varSQL2) {
		try {
			stmt1 = Welcome.conn.prepareCall(varSQL2);
			stmt1.setString(1, T1.getText());
			stmt1.setString(2, T2.getText());
			stmt1.setInt(3, Integer.parseInt(T3.getText().trim()));
			stmt1.setString(4, T4.getText());
			stmt1.registerOutParameter(5, Types.VARCHAR);
			stmt1.executeQuery();
			JOptionPane.showMessageDialog(null, stmt1.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!!", JOptionPane.ERROR_MESSAGE);
		}

	}

	public String[] Return_ID(String Table) {
		int row = 0;
		int i = 1;
		String name = "";
		try {
			Statement stmt1 = Welcome.conn.createStatement();
			String varSQL1 = "SELECT COUNT(*) FROM " + Table;
			ResultSet rs = stmt1.executeQuery(varSQL1);
			rs.next();
			row = (rs.getInt(1) + 1);
			rs.close();
			stmt1.close();
		} catch (SQLException ex) {
		}
		String[] com = new String[row];
		com[0] = ("(Select Option)");
		try {

			Statement stmt1 = Welcome.conn.createStatement();
			String varSQL1 = "SELECT * FROM " + Table;
			ResultSet rs = stmt1.executeQuery(varSQL1);
			while (rs.next()) {
				name = rs.getString(1);
				com[i] = name;
				i++;
			}
			rs.close();
			stmt1.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return com;
	}

	private void AddRecord(String selectedItem)

	{

		// Calling HomeFrame method
		HomeFrame();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setOpaque(false);

		JButton back = new JButton("Back");
		JButton Help = new JButton("Help");
		JPanel sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		sidePanel.setLayout(new GridLayout(5, 1));
		sidePanel.add(back);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Home);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Help);

		JButton OKbutton = new JButton("OK");

		back.setFont(new Font(back.getName(), Font.BOLD, 30));
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String varSQL1 = null;
				viewTable = null;
				contentPane = null;
				stmt1 = null;
				varSQL1 = null;
				itemID = null;
				error_msg = 0;
				T1 = T2 = T3 = T4 = T5 = T6 = T7 = null;

				ViewRecord(selectedItem);

			}

		});

		Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
		Home.addActionListener(new HomeButtonListener());
		OKbutton.setFont(new Font(OKbutton.getName(), Font.BOLD, 30));
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"You have Clicked on the ADD button.\n" + "Now you can add record to the UNKCOEDatabase\n"
								+ "Fill in the blanks with  correct information\n"
								+ "Click ok to add the record to this Database",
						"Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});

		JPanel recordPanel = new JPanel();
		recordPanel.setOpaque(false);

		// Choosing the selectedItem
		switch (selectedItem) {
		case "Address": {
			varSQL1 = "(?,?,?,?,?)";
			recordPanel = Address(OKbutton);
			break;
		}
		case "Computer": {
			varSQL1 = "(?,?,?,?,?,?,?,?)";
			recordPanel = Computer(OKbutton);
			break;
		}
		case "Account": {
			varSQL1 = "(?,?,?,?,?)";
			recordPanel = Account(OKbutton);
			break;
		}
		case "PC": {
			varSQL1 = "(?,?,?,?,?,?)";
			recordPanel = PC(OKbutton);
			break;
		}
		case "IMac": {
			varSQL1 = "(?,?,?,?,?)";
			recordPanel = IMac(OKbutton);
			break;
		}
		case "Laptop": {
			varSQL1 = "(?,?,?,?,?)";
			recordPanel = Laptop(OKbutton);
			break;
		}
		case "OperatingSystem": {
			varSQL1 = "(?,?,?)";
			recordPanel = OperatingSystem(OKbutton);
			break;
		}
		case "Model": {
			varSQL1 = "(?,?,?,?)";
			recordPanel = Model(OKbutton);
			break;
		}
		case "Model_has_OperatingSystem": {
			varSQL1 = "(?,?,?)";
			recordPanel = Model_has_OperatingSystem(OKbutton);
			break;
		}
		case "User": {
			varSQL1 = "(?,?,?,?,?,?)";
			recordPanel = User(OKbutton);
			break;
		}
		case "Role_Type": {
			varSQL1 = "(?,?,?)";
			recordPanel = Role_Type(OKbutton);
			break;
		}
		case "Brand": {
			varSQL1 = "(?,?,?)";
			recordPanel = Brand(OKbutton);
			break;
		}
		default: {
			break;
		}
		}

		recordPanel.setOpaque(false);

		OKbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = null;
				String varSQL2 = null;
				if (!T1.getText().equals("")) {
					varSQL2 = "{CALL UNKCOEInventory." + selectedItem + "Add" + varSQL1 + "}";
					message = "Added";

					// Choosing the selectedItem

					switch (selectedItem) {
					case "Address": {
						ExecuteAddressQuery(varSQL2);
						break;
					}
					case "Computer": {
						ExecuteComputerQuery(varSQL2);
						break;
					}
					case "Account": {
						ExecuteAccountQuery(varSQL2);
						break;
					}
					case "PC": {
						ExecutePCQuery(varSQL2);
						break;
					}
					case "IMac": {
						ExecuteIMacQuery(varSQL2);
						break;
					}
					case "Laptop": {
						ExecuteLaptopQuery(varSQL2);
						break;
					}
					case "OperatingSystem": {
						ExecuteOperatingSystemQuery(varSQL2);
						break;
					}
					case "Model": {
						ExecuteModelQuery(varSQL2);
						break;
					}
					case "Model_has_OperatingSystem": {
						ExecuteModel_has_OperatingSystemQuery(varSQL2);
						break;
					}
					case "User": {
						ExecuteUserQuery(varSQL2);
						break;
					}
					case "Role_Type": {
						ExecuteRole_TypeQuery(varSQL2);
						break;
					}
					case "Brand": {
						ExecuteBrandQuery(varSQL2);
						break;
					}

					}
					// JOptionPane.showMessageDialog(null, "The record has been
					// "+message+ " in UNKCOEInventory Database");
				}
			}
		});

		if (selectedItem.equals("Computer") || selectedItem.equals("Account")) {

			contentPane = Welcome.frame.getContentPane();
			contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, xSize / 2, ySize / 10));
		}
		mainPanel.add(recordPanel);
		mainPanel.add(sidePanel);

		Welcome.frame.add(mainPanel);
		Welcome.frame.revalidate();
		Welcome.frame.repaint();
		Welcome.frame.pack();

	}

	private void UpdateRecord(String selectedItem, Object[] rowdata)

	{

		// Calling HomeFrame method
		HomeFrame();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setOpaque(false);

		JPanel sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		sidePanel.setLayout(new GridLayout(5, 1));

		JButton back = new JButton("Back");
		JButton Help = new JButton("Help");
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		sidePanel.add(back);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Home);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Help);

		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"You have Clicked on the Update button.\n"
								+ "Now you can update the record in the UNKCOEDatabase\n"
								+ "Fill in the blanks with  correct information\n"
								+ "Click ok to Update the record in this Database",
						"Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});
		JButton OKbutton = new JButton("OK");
		back.setFont(new Font(back.getName(), Font.BOLD, 30));
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String varSQL1 = null;
				viewTable = null;
				contentPane = null;
				stmt1 = null;
				varSQL1 = null;
				itemID = null;
				error_msg = 0;
				T1 = T2 = T3 = T4 = T5 = T6 = T7 = null;

				ViewRecord(selectedItem);

			}

		});

		Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
		Home.addActionListener(new HomeButtonListener());
		OKbutton.setFont(new Font(OKbutton.getName(), Font.BOLD, 30));

		JPanel recordPanel = new JPanel();
		recordPanel.setOpaque(false);

		// Choosing the selectedItem
		switch (selectedItem) {
		case "Address": {
			recordPanel = AddressUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?)";
			break;
		}
		case "Computer": {
			recordPanel = ComputerUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?,?,?,?)";
			break;
		}
		case "Account": {
			recordPanel = AccountUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?)";
			break;
		}
		case "PC": {
			recordPanel = PCUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?,?)";
			break;
		}
		case "IMac": {
			recordPanel = IMacUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?)";
			break;
		}
		case "Laptop": {
			recordPanel = LaptopUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?)";
			break;
		}
		case "OperatingSystem": {
			recordPanel = OperatingSystemUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?)";
			break;
		}
		case "Model": {
			recordPanel = ModelUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?)";
			break;
		}
		case "Model_has_OperatingSystem": {
			recordPanel = Model_has_OperatingSystemUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?)";
			break;
		}
		case "User": {
			recordPanel = UserUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?,?,?,?)";
			break;
		}
		case "Role_Type": {
			recordPanel = Role_TypeUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?)";
			break;
		}
		case "Brand": {
			recordPanel = BrandUpdate(rowdata, OKbutton);
			varSQL1 = "(?,?,?)";
			break;
		}
		default: {
			break;
		}
		}

		recordPanel.setOpaque(false);

		OKbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = null;
				String varSQL2 = null;
				if (!T1.getText().equals("")) {
					varSQL2 = "{CALL UNKCOEInventory." + selectedItem + "Update" + varSQL1 + "}";
					message = "Updated";

					// Choosing the selectedItem

					switch (selectedItem) {
					case "Address": {
						ExecuteAddressQuery(varSQL2);
						break;
					}
					case "Computer": {
						ExecuteComputerQuery(varSQL2);
						break;
					}
					case "Account": {
						ExecuteAccountQuery(varSQL2);
						break;
					}
					case "PC": {
						ExecutePCQuery(varSQL2);
						break;
					}
					case "IMac": {
						ExecuteIMacQuery(varSQL2);
						break;
					}
					case "Laptop": {
						ExecuteLaptopQuery(varSQL2);
						break;
					}
					case "OperatingSystem": {
						ExecuteOperatingSystemQuery(varSQL2);
						break;
					}
					case "Model": {
						ExecuteModelQuery(varSQL2);
						break;
					}
					case "Model_has_OperatingSystem": {
						ExecuteModel_has_OperatingSystemQuery(varSQL2);
						break;
					}
					case "User": {
						ExecuteUserQuery(varSQL2);
						break;
					}
					case "Role_Type": {
						ExecuteRole_TypeQuery(varSQL2);
						break;
					}
					case "Brand": {
						ExecuteBrandQuery(varSQL2);
						break;
					}

					}
					// JOptionPane.showMessageDialog(null, "The record has been
					// "+message+ " in UNKCOEInventory Database");
				}
			}
		});

		if (selectedItem.equals("Computer") || selectedItem.equals("Account")) {

			contentPane = Welcome.frame.getContentPane();
			contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, xSize / 2, ySize / 10));
		}
		mainPanel.add(recordPanel);
		mainPanel.add(sidePanel);

		Welcome.frame.add(mainPanel);
		Welcome.frame.revalidate();
		Welcome.frame.repaint();
		Welcome.frame.pack();
	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}
	}

	private class HighlightRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			// everything as usual
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			// added behavior
			if (row == table.getSelectedRow()) {

				// this will customize that kind of border that will be use to
				// highlight the selected row
				setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
			}

			return this;
		}
	}

	public void ViewRecord(String varTableName) {

		HomeFrame();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setOpaque(false);

		JButton add = new JButton("Add");
		JButton update = new JButton("Update");
		JButton delete = new JButton("Delete");
		JButton search = new JButton("Search");
		JButton back = new JButton("Back");
		JButton Help = new JButton("Help");

		JPanel sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		sidePanel.setLayout(new GridLayout(13, 1));
		sidePanel.add(add);
		sidePanel.add(new JLabel(""));
		sidePanel.add(delete);
		sidePanel.add(new JLabel(""));
		sidePanel.add(update);
		sidePanel.add(new JLabel(""));
		sidePanel.add(search);
		sidePanel.add(new JLabel(""));
		sidePanel.add(back);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Home);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Help);

		add.setFont(new Font(add.getName(), Font.BOLD, 30));
		update.setFont(new Font(update.getName(), Font.BOLD, 30));
		delete.setFont(new Font(delete.getName(), Font.BOLD, 30));
		search.setFont(new Font(search.getName(), Font.BOLD, 30));
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"You are now viewing the records of the UNKCOEDatabase.\n"
								+ "You can click on Add to add the record to the UNKCOEDatabase\n"
								+ "You can click on Update to update the record in the UNKCOEDatabase\n"
								+ "You can click on Delete to delete the record in the UNKCOEDatabase\n"
								+ "You can click on Search to search a specific information in the record of UNKCOEDatabase\n"
								+ "You can click on Home to roll back to the home or main page of the UNKCOEDatabase",
						"Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String search = JOptionPane.showInputDialog("Enter   " + A1.getText(), "SearchBox");

				int rows = viewTable.getRowCount();
				if (search != null) {
					for (int i = 0; i < rows; i++) {
						Object value = viewTable.getValueAt(i, 0);
						if (value != null && value.toString().matches(".*" + Pattern.quote(search) + ".*")) {
							// this will automatically set the view of the
							// scroll in the location of the value
							viewTable.scrollRectToVisible(viewTable.getCellRect(i, 0, true));

							// this will automatically set the focus of the
							// searched/selected row/value
							viewTable.setRowSelectionInterval(i, i);

							for (int j = 0; j <= viewTable.getColumnCount() - 1; j++) {

								viewTable.getColumnModel().getColumn(j).setCellRenderer(new HighlightRenderer());
							}
							itemID = (viewTable.getModel().getValueAt(i, 0).toString());
							rowdata = new Object[viewTable.getColumnCount()];
							for (int k = 0; k < viewTable.getColumnCount(); k++) {
								rowdata[k] = viewTable.getValueAt(i, k);
								// System.out.println(new JTextField((String)
								// rowdata[i]).getText());
							}
						}

						// itemID = (viewTable.getModel().getValueAt(i,
						// 0).toString());
						// System.out.println(itemID);
						// if(itemID.equals(search))
						// {
						// viewTable.getSelectedRow();
						// System.out.println("here");
						// }
					}
				}

				// JPanel searchPanel = new JPanel ();
				// searchPanel.setLayout(new GridLayout(3,1));
				// JButton search = new JButton("Search");
				// search.setFont(new Font(search.getName(), Font.BOLD, 30));
				//
				// Dimension d = new Dimension((int)(xSize/6), (int)(ySize/8));
				// searchPanel.setPreferredSize(d);
				// T1 = new JTextField();
				//
				// searchPanel.add(A1);
				// searchPanel.add(T1);
				// searchPanel.add(search);
				//
				//
				// search.addActionListener(new ActionListener()
				// {
				// @Override
				// public void actionPerformed(ActionEvent arg0) {
				//
				// //Something goes here
				//
				// }
				//
				// });
			}

		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				AddRecord(varTableName);
			}

		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (itemID != null) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You want to Update this Record!!",
							"UPDATE!!", JOptionPane.WARNING_MESSAGE);
					if (confirm == 0) {
						UpdateRecord(varTableName, rowdata);

					}
				}

			}

		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (itemID != null) {
					int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure You want to Delete this Record!!",
							"DELETE!!", JOptionPane.WARNING_MESSAGE);
					if (confirm == 0) {
						DeleteRecord(varTableName, rowdata);
					}
				}

			}
		});

		back.setFont(new Font(back.getName(), Font.BOLD, 30));
		back.addActionListener(new ButtonListener());
		Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
		Home.addActionListener(new HomeButtonListener());

		int row = 0;
		try {
			Statement stmt1 = Welcome.conn.createStatement();
			String varSQL1 = "SELECT COUNT(*) FROM " + varTableName;
			ResultSet rs = stmt1.executeQuery(varSQL1);
			rs.next();
			row = rs.getInt(1);
			rs.close();
			stmt1.close();
		} catch (SQLException ex) {
		}

		try {
			Statement stmt = Welcome.conn.createStatement();
			String varSQL = "Select * from " + varTableName;
			ResultSet rs = stmt.executeQuery(varSQL);
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columns = rsMeta.getColumnCount();
			String[][] rowData = new String[row][columns];
			String[] columnNames = new String[columns];
			String Name = "";
			for (int c = 1; c <= columns; c++) {
				Name += rsMeta.getColumnName(c) + " ";
				columnNames[c - 1] = Name;
				Name = "";
			}
			int b = 0;
			while (rs.next()) {
				for (int c = 1; c <= columns; c++) {
					Name += rs.getString(c) + " ";
					rowData[b][c - 1] = Name;
					Name = "";
				}
				b++;
			}
			viewTable = new JTable(rowData, columnNames) {
				@Override

				// Changing the width of the table cells to fit its contents
				// inside the cell
				// Getting rid of the truncated text inside the Table cell
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					Component component = super.prepareRenderer(renderer, row, column);
					int rendererWidth = component.getPreferredSize().width;
					TableColumn tableColumn = getColumnModel().getColumn(column);
					tableColumn.setPreferredWidth(
							Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
					return component;
				}
			};
			viewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			// Setting the table font
			viewTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 25));
			viewTable.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			// Setting the table row height to 40 pixels
			viewTable.setRowHeight(40);

			viewTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					try {

						int selectedRow = viewTable.getSelectedRow();
						itemID = (viewTable.getModel().getValueAt(selectedRow, 0).toString());
						rowdata = new Object[viewTable.getColumnCount()];
						for (int i = 0; i < viewTable.getColumnCount(); i++) {
							rowdata[i] = viewTable.getValueAt(selectedRow, i);
							// System.out.println(new JTextField((String)
							// rowdata[i]).getText());
						}

					} catch (Exception e) {
						System.out.println(e);
					}

				}
			});

			Dimension d = new Dimension((int) (xSize / 1.5), (int) (ySize / 1.5));
			// Adding a JScrollPane to the JPanel for allowing the scrollability
			JScrollPane jScrollpanel = new JScrollPane(viewTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollpanel.setPreferredSize(d);
			// Adjusting the minimum column width for the table
			for (int j = 0; j < columns; j++) {
				viewTable.getColumnModel().getColumn(j).setPreferredWidth(300);

			}

			mainPanel.add(jScrollpanel);
			mainPanel.add(sidePanel);
			Welcome.frame.add(mainPanel, BorderLayout.CENTER);
			Welcome.frame.revalidate();
			Welcome.frame.repaint();
			Welcome.frame.pack();
			rs.close();
			stmt.close();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	protected void DeleteRecord(String varTableName, Object[] rowdata2) {

		// Calling HomeFrame method
		HomeFrame();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.setOpaque(false);

		JPanel sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		sidePanel.setLayout(new GridLayout(5, 1));

		JButton back = new JButton("Back");
		JButton Help = new JButton("Help");
		Help.setFont(new Font(Help.getName(), Font.BOLD, 30));

		sidePanel.add(back);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Home);
		sidePanel.add(new JLabel(""));
		sidePanel.add(Help);

		Help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "You have Clicked on the Delete button.\n"
						+ "Now you can delete record from the UNKCOEDatabase\n" + "Enter the correct information\n"
						+ "Click ok to delete the record from this Database", "Help!", JOptionPane.QUESTION_MESSAGE);
			}

		});
		JButton OKbutton = new JButton("OK");
		back.setFont(new Font(back.getName(), Font.BOLD, 30));
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String varSQL1 = null;
				viewTable = null;
				contentPane = null;
				stmt1 = null;
				varSQL1 = null;
				itemID = null;
				error_msg = 0;
				T1 = T2 = T3 = T4 = T5 = T6 = T7 = null;

				ViewRecord(varTableName);

			}

		});
		Home.setFont(new Font(Home.getName(), Font.BOLD, 30));
		Home.addActionListener(new HomeButtonListener());
		OKbutton.setFont(new Font(OKbutton.getName(), Font.BOLD, 30));

		JPanel recordPanel = new JPanel();
		recordPanel.setOpaque(false);

		// Choosing the selectedItem
		switch (varTableName) {
		case "Address": {
			recordPanel = AddressDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Computer": {
			recordPanel = ComputerDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Account": {
			recordPanel = AccountDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "PC": {
			recordPanel = PCDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "IMac": {
			recordPanel = IMacDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Laptop": {
			recordPanel = LaptopDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "OperatingSystem": {
			recordPanel = OperatingSystemDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Model": {
			recordPanel = ModelDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Model_has_OperatingSystem": {
			recordPanel = Model_has_OperatingSystemDelete(rowdata, OKbutton);
			varSQL1 = "(?,?,?)";
			break;
		}
		case "User": {
			recordPanel = UserDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Role_Type": {
			recordPanel = Role_TypeDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		case "Brand": {
			recordPanel = BrandDelete(rowdata, OKbutton);
			varSQL1 = "(?,?)";
			break;
		}
		default: {
			break;
		}
		}

		recordPanel.setOpaque(false);

		OKbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!T1.getText().equals("")) {
					String varSQL2 = "{CALL UNKCOEInventory." + varTableName + "Delete" + varSQL1 + "}";
					try {
						System.out.println("here");
						stmt1 = Welcome.conn.prepareCall(varSQL2);
						stmt1.setString(1, T1.getText().trim());
						if (varTableName.equals("Model_has_OperatingSystem")) {
							stmt1.setString(2, T2.getText().trim());
							stmt1.registerOutParameter(3, Types.VARCHAR);
							stmt1.executeQuery();
							JOptionPane.showMessageDialog(null, stmt1.getString(3));
						} else {
							stmt1.registerOutParameter(2, Types.VARCHAR);
							stmt1.executeQuery();
							JOptionPane.showMessageDialog(null, stmt1.getString(2));
						}

					} catch (SQLException j) {
						JOptionPane.showMessageDialog(null, "Error!!", j.getMessage(), JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		mainPanel.add(recordPanel);
		mainPanel.add(sidePanel);

		Welcome.frame.add(mainPanel);
		Welcome.frame.revalidate();
		Welcome.frame.repaint();
		Welcome.frame.pack();
	}

}
