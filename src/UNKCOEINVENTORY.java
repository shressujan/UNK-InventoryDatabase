
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * IMPORTANT NOTE: Author: Sujan Shrestha Reference: Sample Project given by
 * Prof Sherri Harms This class is where a connection is made between your
 * computer and the UNK server Every time this code is compiled a session is
 * created in which you will be connected to your database on the UNK server You
 * will need to change the database to your own database to run this sample. We
 * need the following jar files on the buildpath for this program to run:
 * mysql-connector-java-XXX.jar //to connect Java to MySQL jsch-XXX.jar //for
 * the sessions
 */

public class UNKCOEINVENTORY {
	public static void main(String[] args) {

		// Components related to "login" field
		JLabel labelLoginName = new JLabel("Enter your server user name:");
		JTextField loginName = new JTextField(15);

		// Components related to "password" field
		JLabel labelPassword = new JLabel("Enter your server password:");
		JPasswordField password = new JPasswordField();

		Object[] array = { labelLoginName, loginName, labelPassword, password };

		int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		int assigned_port = 0;
		final int local_port = 3306;

		// Remote host and port
		final int remote_port = 3306;
		final String remote_host = "cs.unk.edu";

		try {
			JSch jsch = new JSch();

			// Create SSH session. Port 22 is your SSH port which
			// is open in your fire-wall setup.
			Session session = jsch.getSession(loginName.getText(), remote_host, 22);
			String passwordString = new String(password.getPassword());
			session.setPassword(passwordString);

			// Additional SSH options. See your ssh_config manual for
			// more options. Set options according to your requirements.
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("Compression", "yes");
			config.put("ConnectionAttempts", "2");

			session.setConfig(config);

			// Connect
			session.connect();
			// System.out.println("Host: "+session.getHost());

			// Create the tunnel through port forwarding.
			// This is basically instructing jsch session to send
			// data received from local_port in the local machine to
			// remote_port of the remote_host
			// assigned_port is the port assigned by jsch for use,
			// it may not always be the same as
			// local_port.

			assigned_port = session.setPortForwardingL(local_port, "127.0.0.1", remote_port);

		} catch (JSchException e1) {
			System.out.println(Level.SEVERE + " " + e1.getMessage());
			System.exit(0);
		}

		if (assigned_port == 0) {
			System.out.println(Level.SEVERE + " " + "Port forwarding failed !");
			System.exit(0);
			return;
		}

		System.out.println("I made it this far!!");

		// Instantiating the Constructor
		Welcome welcome = new Welcome();
	}

}
