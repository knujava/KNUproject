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
		setTitle("메시지");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		JLabel message = new JLabel("빈칸이 있습니다. 채워주세요");
		message.setFont(new Font("SansSerif", Font.BOLD, 20));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setVerticalAlignment(SwingConstants.CENTER);

		add(message, BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton ok = new JButton("확인");
		ok.addActionListener(this);
		bp.add(ok);
		ok.setMargin(new Insets(10,40,10,40));
		add(bp, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("확인")) {
			dispose();
		}
	}
}

public class Mainform extends JFrame implements ActionListener, MouseListener{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	private int num_of_student;
	private JPanel up = new JPanel(); 
	private JPanel down = new JPanel(); // up은 검색, down은 보여주기
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
		p1.add(new JLabel("학번"));
		p1.add(Num);
		up.add(p1);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("PW"));
		p2.add(PW);
		up.add(p2);

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(new JLabel("이름"));
		p3.add(Name);
		up.add(p3);

		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		p4.add(new JLabel("과목명"));
		p4.add(Subject);
		up.add(p4);

		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout());
		p5.add(new JLabel("성적"));
		p5.add(Grade);
		up.add(p5);

		add(up);
		add(down);

		JMenuBar menu = new JMenuBar();
		JMenu Student = new JMenu("학생");
		JMenu grade = new JMenu("성적");
		JMenuItem grade_item = new JMenuItem("성적 정보");
		JMenuItem detail = new JMenuItem("자세히 보기");
		detail.addActionListener(this);
		grade_item.addActionListener(this);
		
		Student.add(detail);
		grade.add(grade_item);
		menu.add(Student);
		menu.add(grade);
		setJMenuBar(menu);

		down.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();

		JButton add = new JButton("등록");
		add.addActionListener(this);

		JButton change = new JButton("수정");
		change.addActionListener(this);

		JButton delete = new JButton("삭제");
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
		String header[] = {"학번","이름","과목명","성적"};
		String contents[][] = new String[num_of_student][4];
		/*
			{"2016000000", "김철수", "심리학", "4.3"},
			{"2015000000", "조짱구", "자바", "0.7"},
			{"2017000000", "최유리", "자료구조", "3.3"},
			{"2018000000", "박맹구", "팔공산", "1.7"},
			{"2019000000", "이훈", "초급교양영어", "2.7"}
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

		if(command.equals("등록")){
			String num = Num.getText();
			String name = Name.getText();
			String subject = Subject.getText();
			String grade = Grade.getText();

			if(num.equals("") || name.equals("") || subject.equals("") || grade.equals("")) {
				Error error = new Error();  // 빈칸이 있으면 에러 창 띄우기
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
		else if(command.equals("수정")){
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
						// 수정한 문자열 일 경우
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
		else if(command.equals("삭제")){
			int row = table.getSelectedRow();
			model.removeRow(row);
			
			//삭제 한 것을 파일에도 반영시키기
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
		else if(command.equals("자세히 보기")) {
			// 자세히 보는 화면 띄우기
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
