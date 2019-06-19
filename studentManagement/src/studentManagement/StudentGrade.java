package studentManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentGrade extends JFrame implements ActionListener{
	public StudentGrade() {
		super("WELCOME! :::: student");
		setSize(500,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String header[] = {"학번","이름","과목명","평점"};
		String contents[][] = {
				{"2016000000", "김철수", "심리학", "4.3"},
				{"2015000000", "조짱구", "자바", "0.7"},
				{"2017000000", "최유리", "자료구조", "3.3"},
				{"2018000000", "박맹구", "팔공산", "1.7"},
				{"2019000000", "이훈", "초급교양영어", "2.7"}
		};
		
		
		JTable table = new JTable(contents, header);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane);
		pack();//
		
		JButton actionButton = new JButton("hid");
		actionButton.addActionListener(this);//
		add(actionButton);
	}
	//화면구성하기
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if (buttonString.contentEquals("")) {
			
			
		}
		
		
	}
}
