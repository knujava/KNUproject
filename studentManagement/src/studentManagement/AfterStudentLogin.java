package studentManagement;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AfterStudentLogin extends JFrame{
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 500;

	private BorderLayout bl = new BorderLayout();
	private JFrame AfterLoginframe;
	private JTable table;
	private JScrollPane scrollpane;

	AfterStudentLogin(String id) throws IOException{
		String header[] = {"과목","학점"};
		String contents[][];
		int i,j;
		StringTokenizer st;
		int linecount=0;
		int allcount =0;
		try(Scanner sc = new Scanner(new FileInputStream("Student.txt"))){
			while(sc.hasNextLine()) {
				if(sc.next().equals(id)) {
					linecount++;
				}
				allcount++;
				sc.nextLine();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		contents = new String[allcount][4];

		DefaultTableModel defaultTableModel = new DefaultTableModel(contents,header);
		table = new JTable(defaultTableModel);
		scrollpane = new JScrollPane(table);
		try {
			Scanner sc = new Scanner(new FileInputStream("Student.txt"));
			for(i = 0 ; i < allcount ; i++) {
				for(j = 0 ; j < 4;j++) {
					if(sc.hasNext()== true) {
						String temp = sc.next();
						contents[i][j] = temp;	
					}
				}
			}
			j=0;
			for(i =0;i<allcount;i++) {
				if(id.equals(contents[i][0]) == true){
					table.setValueAt(contents[i][2], j, 0);
					table.setValueAt(contents[i][3], j, 1);
					j++;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		add(scrollpane);

		setTitle("정보 조회");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenu scoreMenu = new JMenu("Score");
		JMenu studentMenu = new JMenu("Student");
		JMenuItem search = new JMenuItem("search");
		JMenuBar bar = new JMenuBar();

		scoreMenu.add(search);

		bar.add(scoreMenu);
		bar.add(studentMenu);
		add(bar,bl.NORTH);

		setJMenuBar(bar);
	}
}