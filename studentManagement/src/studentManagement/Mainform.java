package studentManagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class main extends JFrame{
	// ��ư�� ���Ƿ� ����� Login�� ���������� ������ main ȯ���� ����� ����
	public static void main(String[] args) {
		main prac = new main();
		prac.setVisible(true);
	}
	public main() {
		setSize(500, 400);
		setTitle("��ư ����");
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
		if(command.equals("Login")) { // �α��� ��ư�� �������� �� ������ ����ȯ���� ������
			Mainform mainScreen = new Mainform();
			mainScreen.setVisible(true);
		}
	}
}
