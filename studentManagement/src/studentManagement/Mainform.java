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
	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public Mainform() {
		setSize(WIDTH, HEIGHT);
		setTitle("Main Form");
		setLayout(new GridLayout(2,1));
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Login")) { // 로그인 버튼이 눌려지고 그 이후의 메인환경을 구성함
			Mainform mainScreen = new Mainform();
			mainScreen.setVisible(true);
		}
	}
}
