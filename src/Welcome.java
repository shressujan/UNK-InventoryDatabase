
/**
 * Author: Sujan Shrestha
 * This class  is for the welcome screen of my database
 * Last modified: 12-01-2016
 * */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql. ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;


public class Welcome{


	public static JFrame frame =  null;
	public static final String database = "UNKCOEInventory";
	public static Connection conn = null;
	private static JTextField loginName1 = null;
	private static JPasswordField password1 = null;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static int xSize = ((int) tk.getScreenSize().getWidth());
	private static int ySize = ((int) tk.getScreenSize().getHeight());

	public Welcome() 
	{
		frame =  new JFrame();
		frame.setTitle("UNKCOEInventory");
		frame.setIconImage(new ImageIcon(this.getClass().getResource("\\images\\BellTower.jpg") ).getImage());
		frame.setSize(xSize,ySize);
		Dimension preferredSize =  new Dimension(xSize, ySize);
		frame.setPreferredSize(preferredSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setVisible(true);

		//Calling the LOGIN Method;
		LOGIN();

	}

	private  void LOGIN()
	{

		ImageIcon img = new ImageIcon (this.getClass().getResource("\\images\\fountain.jpg") );
		Image image = img.getImage(); // transform it 
		Image newimg = image.getScaledInstance(xSize, ySize,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		ImageIcon newImage = new ImageIcon(newimg);

		JLabel background = new JLabel(newImage);
		background.setLayout( new FlowLayout() );
		frame.setContentPane( background );

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, xSize/2,ySize/6));

		JPanel panel = new JPanel(new GridLayout(9,3));
		panel.setOpaque(false);


		JLabel welcome = new JLabel("   Welcome!!  ", SwingConstants.CENTER);
		welcome.setForeground(Color.BLUE);
		welcome.setBackground(Color.LIGHT_GRAY);
		welcome.setOpaque(true);
		welcome.setFont(new Font(welcome.getName(), Font.BOLD, 60));


		// Components related to database "login" field
		JLabel labelLoginName1 = new JLabel("Enter Your Database User Name:   ");
		labelLoginName1.setFont(new Font(labelLoginName1.getName(), Font.BOLD, 30));
		
		labelLoginName1.setForeground(Color.BLUE);

		loginName1 = new JTextField(15);
		loginName1.setFont(new Font(loginName1.getName(), Font.PLAIN, 30));


		// Components related to database "password" field
		JLabel labelPassword1 = new JLabel("Enter Your Database Password:   ");
		labelPassword1.setFont(new Font(labelPassword1.getName(), Font.BOLD, 30));
		labelPassword1.setForeground(Color.BLUE);

		password1 = new JPasswordField();
		password1.setFont(new Font(password1.getName(), Font.PLAIN, 30));

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginButtonListener());
		loginButton.setForeground(Color.BLUE);
		loginButton.setFont(new Font(loginButton.getName(), Font.PLAIN, 40));


		panel.add(new JLabel(""));
		panel.add(welcome);
		panel.add(new JLabel(""));

		panel.add(new JLabel(""));panel.add(new JLabel(""));panel.add(new JLabel(""));
		panel.add(new JLabel(""));panel.add(new JLabel(""));panel.add(new JLabel(""));
		panel.add(new JLabel(""));panel.add(new JLabel(""));panel.add(new JLabel(""));
	

		panel.add(labelLoginName1);
		panel.add(loginName1);
		panel.add(new JLabel(""));

		panel.add(new JLabel(""));panel.add(new JLabel(""));panel.add(new JLabel(""));

		panel.add(labelPassword1);
		panel.add(password1);
		panel.add(new JLabel(""));

		panel.add(new JLabel(""));panel.add(new JLabel(""));panel.add(new JLabel(""));

		panel.add(new JLabel(""));
		panel.add(loginButton);
		panel.add(new JLabel(""));


		frame.add(panel, BorderLayout.CENTER);
		frame.pack();

	}



	private class LoginButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			String user = loginName1.getText();
			String passwordString1 = new String(password1.getPassword());
			if(user.equals("UNKCOEADMIN") & passwordString1.equals("UNKCOEADMIN"))
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					try {

						StringBuilder url1 =
								new StringBuilder("jdbc:mysql://localhost:3306");
						url1.append ("/").append(database).append("?zeroDateTimeBehavior=convertToNull");
						System.out.println(url1);
						conn = DriverManager.getConnection(url1.toString(),user,passwordString1);
						System.out.println("Should be connected");

						//Instantiating the database constructor
						Database_Window database =  new Database_Window();
						
					} catch (SQLException ex) 
					{
						// handle any errors
						System.out.println("SQLException: " + ex.getMessage());
						System.out.println("SQLState: " + ex.getSQLState());
						System.out.println("VendorError: " + ex.getErrorCode());
					}


				}
				catch (Exception ex) 
				{
					// handle the error
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Enter UserName and Password Correctly!!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}

