package studentManagement;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener{
	public static void main(String[] args) {
		mainScreen gui = new mainScreen();
		gui.setVisible(true);
	}

	public mainScreen() {
		super("WELCOME! :::: Professor");
		setSize(500,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String header[] = {"a","b"};
		String contents[][] = {
				{"박", "김"},
				{"이", "최"},
				{"조", "권"}
		};
		
		JTable table = new JTable(contents, header);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane);
		pack();//
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
