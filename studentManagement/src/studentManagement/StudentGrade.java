package studentManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StudentGrade extends JFrame implements ActionListener{
	
	
	private JPanel tablePanel;
	private JTextField name;
	private JTextField grade;
	private JTextField subj;
	
	
	public StudentGrade() {
		super("WELCOME! :::: PROFESSOR");
		setSize(500,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		String header[] = {"학번","이름","과목명","평점"};
		String contents[][] = {
				{"2016000000", "김철수", "심리학", "4.3"},
				{"2015000000", "조짱구", "자바", "0.7"},
				{"2017000000", "최유리", "자료구조", "3.3"},
				{"2018000000", "박맹구", "팔공산", "1.7"},
				{"2019000000", "이훈", "초급교양영어", "2.7"}
		};
		tablePanel = new JPanel();
		
		JTable table = new JTable(contents, header);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane, BorderLayout.NORTH);
		//pack();//
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		
		JButton actionButton_add = new JButton("추가");
		actionButton_add.addActionListener(this);//
		buttonPanel.add(actionButton_add);
		
		JButton actionButton_del = new JButton("제거");
		actionButton_del.addActionListener(this);//
		buttonPanel.add(actionButton_del);
		
		JButton actionButton_re = new JButton("변경사항저장");
		actionButton_re.addActionListener(this);//
		buttonPanel.add(actionButton_re);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());

		JLabel nameps = new JLabel("이름 : ");
		textPanel.add(nameps);
		
		name = new JTextField(10);
		textPanel.add(name);
		
		JLabel subjps = new JLabel("과목 : ");
		textPanel.add(subjps);
		
		subj = new JTextField(10);
		textPanel.add(subj);
		
		JLabel gradeps = new JLabel("성적 : ");
		textPanel.add(gradeps);
		
		grade = new JTextField(10);
		textPanel.add(grade);
		
		
		
		add(textPanel, BorderLayout.CENTER);
	}
	//화면구성하기
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if (buttonString.contentEquals("변경사항저장")) {
			
			System.exit(0);
		}
		else if (buttonString.contentEquals("추가")) {
			
			
		}
		else if (buttonString.contentEquals("제거")) {
			
			
		}
		
	}
}
