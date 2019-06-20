package studentManagement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

class Error extends JFrame implements ActionListener{
	public Error(){
		setSize(400, 200);
		setTitle("�޽���");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JLabel message = new JLabel("��ĭ�� �ֽ��ϴ�. ä���ּ���");
		message.setFont(new Font("SansSerif", Font.BOLD, 20));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setVerticalAlignment(SwingConstants.CENTER);

		add(message, BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton ok = new JButton("Ȯ��");
		ok.addActionListener(this);
		bp.add(ok);
		ok.setMargin(new Insets(10,40,10,40));
		add(bp, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Ȯ��")) {
			dispose();
		}
	}
}

public class Mainform extends JFrame implements ActionListener, MouseListener{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	private int num_of_student;
	private JPanel up = new JPanel(); 
	private JPanel down = new JPanel(); // up�� �˻�, down�� �����ֱ�
	private JTextField Num = new JTextField(30);
	private JTextField PW = new JTextField(30);
	private JTextField Name = new JTextField(30);
	private JTextField Subject = new JTextField(30);
	private JTextField Grade = new JTextField(30);

	private JTable table = new JTable();
	private DefaultTableModel model;

	public Mainform() {
		setSize(WIDTH, HEIGHT);
		setTitle("Main Form");
		setLayout(new GridLayout(2,1));
		up.setLayout(new FlowLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(new JLabel("�й�"));
		p1.add(Num);
		up.add(p1);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("PW"));
		p2.add(PW);
		up.add(p2);

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(new JLabel("�̸�"));
		p3.add(Name);
		up.add(p3);

		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		p4.add(new JLabel("�����"));
		p4.add(Subject);
		up.add(p4);

		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		p5.add(new JLabel("����"));
		p5.add(Grade);
		up.add(p5);

		add(up);
		add(down);

		JMenuBar menu = new JMenuBar();
		JMenu Student = new JMenu("�л�");
		JMenu grade = new JMenu("����");
		JMenuItem grade_item = new JMenuItem("���� ����");
		JMenuItem detail = new JMenuItem("�ڼ��� ����");
		detail.addActionListener(this);
		grade_item.addActionListener(this);
		
		Student.add(detail);
		grade.add(grade_item);
		menu.add(Student);
		menu.add(grade);
		setJMenuBar(menu);

		down.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();

		JButton add = new JButton("���");
		add.addActionListener(this);

		JButton change = new JButton("����");
		change.addActionListener(this);

		JButton delete = new JButton("����");
		delete.addActionListener(this);

		buttonPanel.add(add);
		buttonPanel.add(change);
		buttonPanel.add(delete);
		down.add(buttonPanel, BorderLayout.SOUTH);
		num_of_student = 0;

		try (Scanner sc = new Scanner(new FileInputStream("Student.txt"))){
			while(sc.hasNextLine()) {
				String temp = sc.nextLine();
				num_of_student++;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		String header[] = {"�й�","�̸�","�����","����"};
		String contents[][] = new String[num_of_student][4];
		/*
			{"2016000000", "��ö��", "�ɸ���", "4.3"},
			{"2015000000", "��¯��", "�ڹ�", "0.7"},
			{"2017000000", "������", "�ڷᱸ��", "3.3"},
			{"2018000000", "�ڸͱ�", "�Ȱ���", "1.7"},
			{"2019000000", "����", "�ʱޱ��翵��", "2.7"}
		 */
		try (Scanner sc = new Scanner(new FileInputStream("Student.txt"))){
			for(int i=0; i<num_of_student; i++) {
				for(int j=0; j<4; j++) {
					String temp = sc.next();
					contents[i][j] = temp;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		model = new DefaultTableModel(contents, header){
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		table.setModel(model);

		table.addMouseListener(this);
		JScrollPane Scrollpane = new JScrollPane(table);
		down.add(Scrollpane, BorderLayout.CENTER);

	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if(command.equals("���")){
			String num = Num.getText();
			String name = Name.getText();
			String subject = Subject.getText();
			String grade = Grade.getText();

			if(num.equals("") || name.equals("") || subject.equals("") || grade.equals("")) {
				Error error = new Error();  // ��ĭ�� ������ ���� â ����
				error.setVisible(true);
			}
			else {
				model.addRow(new Object[] {num, name, subject, grade});
				try(BufferedWriter out = new BufferedWriter(new FileWriter("Student.txt", true))){
					out.append("\r\n"+num +" " + name + " "+ subject + " "+ grade);
				}
				catch(IOException d) {
					d.printStackTrace();
				}
				num_of_student++;
			}
		}
		else if(command.equals("����")){
			int row = table.getSelectedRow();

			model.removeRow(row);

			String num = Num.getText();
			String name = Name.getText();
			String subject = Subject.getText();
			String grade = Grade.getText();

			model.addRow(new Object[] {num, name, subject, grade});
			
			String[] temp = new String[num_of_student];
			try(Scanner sc = new Scanner(new FileInputStream("Student.txt"))) {
				for(int i=0; i<num_of_student; i++) {
					String line = sc.nextLine();
					if(i != row) {
						temp[i] = line;
					}
					else if(i==row){
						// ������ ���ڿ� �� ���
						temp[i] = num+" " + name + " " + subject +" "+grade;
					}
				}
			}
			catch(IOException d) {
				d.printStackTrace();
			}
			try(BufferedWriter out = new BufferedWriter(new FileWriter("Student.txt"))) {
				for(int i=0; i<num_of_student-1; i++) {
					out.write(temp[i]+"\r\n");
				}
				out.write(temp[num_of_student-1]);
			}
			catch(IOException d) {
				d.printStackTrace();
			}
		}
		else if(command.equals("����")){
			int row = table.getSelectedRow();
			model.removeRow(row);
			
			//���� �� ���� ���Ͽ��� �ݿ���Ű��
			String[] temp = new String[num_of_student-1];
			int line_count = 0;
			try(Scanner sc = new Scanner(new FileInputStream("Student.txt"))) {
				for(int i=0; i<num_of_student; i++) {
					String line = sc.nextLine();
					if(i != row) {
						temp[line_count++] = line;
					}
				}
			}
			catch(IOException d) {
				d.printStackTrace();
			}
			try(BufferedWriter out = new BufferedWriter(new FileWriter("Student.txt"))) {
				for(int i=0; i<line_count-1; i++) {
					out.write(temp[i]+"\r\n");
				}
				out.write(temp[line_count-1]);
				num_of_student--;
			}
			catch(IOException d) {
				d.printStackTrace();
			}
		}
		else if(command.equals("�ڼ��� ����")) {
			// �ڼ��� ���� ȭ�� ����
			StudentGrade detail_show = new StudentGrade();
			detail_show.setVisible();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		Num.setText((String)table.getValueAt(row, 0));
		Name.setText((String)table.getValueAt(row, 1));
		Subject.setText((String)table.getValueAt(row, 2));
		Grade.setText((String)table.getValueAt(row, 3));
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
