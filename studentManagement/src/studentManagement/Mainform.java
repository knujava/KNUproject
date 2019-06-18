package studentManagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class main extends JFrame{
	// 버튼을 임의로 만들고 Login이 눌려졌을때 이후의 main 환경을 만들기 위해
	public static void main(String[] args) {
		main prac = new main();
		prac.setVisible(true);
	}
	public main() {
		setSize(500, 400);
		setTitle("버튼 예제");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton b = new JButton("Login");
		b.addActionListener(new Mainform());
		add(b, BorderLayout.SOUTH);
	}
}

public class Mainform extends JFrame implements ActionListener{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 500;
	
	private JPanel up = new JPanel(); 
	private JPanel down = new JPanel(); // up은 검색, down은 보여주기
	private JTextField Num = new JTextField(25);
	private JTextField PW = new JTextField(25);
	private JTextField Name = new JTextField(25);
	private JTextField Subject = new JTextField(25);
	
	public Mainform() {
		setSize(WIDTH, HEIGHT);
		setTitle("Main Form");
		setLayout(new GridLayout(2,1));
		up.setLayout(new GridLayout(4,1));
		
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
		p4.add(new JLabel("학과"));
		p4.add(Subject);
		up.add(p4);
		
		add(up);
		add(down);
		
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Login")) { // 로그인 버튼이 눌려지고 그 이후의 메인환경을 구성함
			Mainform mainScreen = new Mainform();
			mainScreen.setVisible(true);
		}
	}
}
