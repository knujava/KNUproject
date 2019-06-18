package studentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginScreen extends JFrame implements ActionListener, KeyListener{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 200;
	private JLabel stateScreen = new JLabel("학번을 입력하십시오");
	
	public LoginScreen() {
		super("학적 관리 프로그램");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		add(stateScreen, BorderLayout.NORTH);
		stateScreen.setHorizontalAlignment(SwingConstants.CENTER);//글자 가운데 정렬
		stateScreen.setVerticalAlignment(SwingConstants.CENTER);
		
		JPanel InputPanel = new JPanel();
		add(InputPanel,BorderLayout.CENTER);
		InputPanel.setLayout(new FlowLayout());
		JPanel IDField = new JPanel();
		InputPanel.add(IDField);
		IDField.setLayout(new FlowLayout());
		IDField.add(new JLabel("ID"));
		JTextField IDinput = new JTextField(10);
		IDField.add(IDinput);
		JPanel PWField = new JPanel();
		InputPanel.add(PWField);
		PWField.setLayout(new FlowLayout());
		PWField.add(new JLabel("PW"));
		JPasswordField PWinput = new JPasswordField(10);
		PWField.add(PWinput);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(this);
		InputPanel.add(LoginButton);
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 13) {
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private boolean searchID(String ID, String PW) {
		
		return false;
	}
}
