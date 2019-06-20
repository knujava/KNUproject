package studentManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StudentGrade extends JFrame implements ActionListener, MouseListener{
	

	private JTextField num;
	private JTextField name;
	private JTextField grade;
	private JTextField subj;

	
	private int rowcnt = 0;
	private int colcnt = 0;
	
	private JTable table = new JTable();
	private DefaultTableModel model;
	
	private int counterarrsize = 0;
	
	private String[][] contents;
	
	

	
	public StudentGrade() {
		super("WELCOME! :::: PROFESSOR");
		setSize(700,900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JMenu usermenu = new JMenu("메뉴");
		
		JMenuItem alphaChoice = new JMenuItem("창닫기");
		alphaChoice.addActionListener(this);
		usermenu.add(alphaChoice);
		
		JMenuBar bar =new JMenuBar();
		bar.add(usermenu);
		setJMenuBar(bar);
		
		String header[] = {"학번","이름","과목명","평점"};
		//전역
		
		//String contents[][] = {
		//		{"2016000000", "김철수", "심리학", "4.3"},
		//		{"2015000000", "조짱구", "최고과목자바", "0.7"},
		//		{"2017000000", "최유리", "테니스", "3.3"},
		//		{"2018000000", "박맹구", "팔공산", "1.7"},
		//		{"2019000000", "이훈", "초급교양영어", "2.7"}
		//};

		
	
		
		try (Scanner sc = new Scanner(new FileInputStream("Student.txt"))){
			while(sc.hasNextLine()) {
				sc.nextLine();
				counterarrsize++;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		//알아서 닫힘
		
		contents = new String[counterarrsize][4];
		
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream("Student.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("no file");
			System.exit(0);
		}
		
		while(inputStream.hasNextLine())
		{
			String tmp = inputStream.nextLine();
			StringTokenizer st = new StringTokenizer(tmp);
			

		
			while (st.hasMoreTokens()) {
			contents[rowcnt][colcnt] = st.nextToken();
			colcnt++;
			}
			
			rowcnt++;
			colcnt = 0;
		}
		
		inputStream.close();
		
		model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		table = new JTable(model);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane, BorderLayout.CENTER);
		//pack();//
		table.getTableHeader().setReorderingAllowed(false);
		
			
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(2, 1));
		
		//
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());

		JLabel numps = new JLabel("학번:");
		textPanel.add(numps);
		
		num = new JTextField(9);
		//num.setEditable(false);
		textPanel.add(num);
		
		JLabel nameps = new JLabel("이름:");
		textPanel.add(nameps);
		
		name = new JTextField(9);
		//name.setEditable(false);
		textPanel.add(name);
		
		JLabel subjps = new JLabel("과목:");
		textPanel.add(subjps);
		
		subj = new JTextField(9);
		textPanel.add(subj);
		
		JLabel gradeps = new JLabel("성적:");
		textPanel.add(gradeps);
		
		grade = new JTextField(9);
		textPanel.add(grade);
		
	
		JButton searchButton = new JButton(" ");
		ImageIcon magIcon = new ImageIcon("mag.png");
		searchButton.setIcon(magIcon);
		searchButton.addActionListener(this);
		searchButton.setBackground(Color.WHITE);
		textPanel.add(searchButton);
		
		userPanel.add(textPanel);

		JPanel buttonPanel = new JPanel();

		
		add(userPanel, BorderLayout.SOUTH);
		
		table.addMouseListener(this);
		

		
		
	}
	//화면구성하기
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if (buttonString.contentEquals(" ")) {
//			String numi = num.getText();
//			String namei = name.getText();
//			String subjecti = subj.getText();
//			String gradei = grade.getText();
//			
//			String header[] = {"학번","이름","과목명","평점"};
//			
//			int cnt = 0;
//			
//			for(int i = 0; i < counterarrsize; i++) {
//				if(numi.equals(contents[i][0]) 
//						|| namei.equals(contents[i][1])
//						||subjecti.equals(contents[i][2]) 
//						|| gradei.equals(contents[i][3])) {
//					
//					cnt++;
//					
//					model.addRow(new Object[] {numi, namei, subjecti, gradei});
//				}
//			}

		

		
	
		}

		else if (buttonString.contentEquals("창닫기")) {
			
			setVisible(false);
		}
	}		

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		num.setText((String)table.getValueAt(row,0));
		name.setText((String)table.getValueAt(row, 1));
		subj.setText((String)table.getValueAt(row, 2));
		grade.setText((String)table.getValueAt(row, 3));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}



