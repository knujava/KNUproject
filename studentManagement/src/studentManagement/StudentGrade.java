package studentManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
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

public class StudentGrade extends JFrame implements ActionListener{
	
	
	private JPanel tablePanel;
	private JTextField num;
	private JTextField name;
	private JTextField grade;
	private JTextField subj;
	
	private int rowcnt = 0;
	private int colcnt = 0;
	
	
	public StudentGrade() {
		super("WELCOME! :::: PROFESSOR");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JMenu usermenu = new JMenu("�з�");
		
		JMenuItem alphaChoice = new JMenuItem("alpha");
		alphaChoice.addActionListener(this);
		usermenu.add(alphaChoice);
		
		JMenuBar bar =new JMenuBar();
		bar.add(usermenu);
		setJMenuBar(bar);
		
		String header[] = {"�й�","�̸�","�����","����"};
		String contents[][] = new String[100][4];
		
		//String contents[][] = {
		//		{"2016000000", "��ö��", "�ɸ���", "4.3"},
		//		{"2015000000", "��¯��", "�ڹ�", "0.7"},
		//		{"2017000000", "������", "�ڷᱸ��", "3.3"},
		//		{"2018000000", "�ڸͱ�", "�Ȱ���", "1.7"},
		//		{"2019000000", "����", "�ʱޱ��翵��", "2.7"}
		//};
		tablePanel = new JPanel();//??
		
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new FileInputStream("stugrade.txt"));
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
			
			//contents[rowcnt][colcnt] = st;
		
			while (st.hasMoreTokens()) {
			contents[rowcnt][colcnt] = st.nextToken();
			colcnt++;
			}
			
			rowcnt++;
			colcnt = 0;
		}
		
		inputStream.close();
		
		DefaultTableModel model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		JTable table = new JTable(model);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane, BorderLayout.CENTER);
		//pack();//
		table.getTableHeader().setReorderingAllowed(false);
		
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(2, 1));
		
		//
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());

		JLabel numps = new JLabel("�й�:");
		textPanel.add(numps);
		
		num = new JTextField(7);
		num.setEditable(false);
		textPanel.add(num);
		
		JLabel nameps = new JLabel("�̸�:");
		textPanel.add(nameps);
		
		name = new JTextField(7);
		name.setEditable(false);
		textPanel.add(name);
		
		JLabel subjps = new JLabel("����:");
		textPanel.add(subjps);
		
		subj = new JTextField(7);
		textPanel.add(subj);
		
		JLabel gradeps = new JLabel("����:");
		textPanel.add(gradeps);
		
		grade = new JTextField(7);
		textPanel.add(grade);
		
		userPanel.add(textPanel);
		//
		
		//
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		
		JButton actionButton_add = new JButton("�߰�");
		actionButton_add.addActionListener(this);//
		buttonPanel.add(actionButton_add);
		
		JButton actionButton_del = new JButton("����");
		actionButton_del.addActionListener(this);//
		buttonPanel.add(actionButton_del);
		
		JButton actionButton_re = new JButton("�����������");
		actionButton_re.addActionListener(this);//
		buttonPanel.add(actionButton_re);
		
		userPanel.add(buttonPanel);
		//
		
		add(userPanel, BorderLayout.SOUTH);
	}
	//ȭ�鱸���ϱ�
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if (buttonString.contentEquals("�����������")) {
			
			System.exit(0);
		}
		else if (buttonString.contentEquals("�߰�")) {
			
			
		}
		else if (buttonString.contentEquals("����")) {
			
			
		}
		
	}
}