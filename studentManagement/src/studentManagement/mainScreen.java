package studentManagement;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener{
	public static void main(String[] args) {
		mainScreen gui = new mainScreen();
		gui.setVisible(true);
	}

	static String contents[][] = {
			{"박", "김"},
			{"이", "최"},
			{"조", "권"}
	};
	
	public mainScreen() {
		super("WELCOME! :::: student");
		setSize(500,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String header[] = {"a","b"};
		
		
		JTable table = new JTable(contents, header);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane);
		pack();//
		
		JButton actionButton = new JButton("hid");
		actionButton.addActionListener(this);//
		add(actionButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		System.exit(0);
		
	}
}
