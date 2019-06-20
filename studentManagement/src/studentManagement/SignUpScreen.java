package studentManagement;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpScreen extends JFrame implements ActionListener{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 200;
	private JLabel stateScreen = new JLabel("등록할 ID 및 PW 입력");
	private JTextField IDinput;
	private JPasswordField PWinput;
	private JButton apply;
	private JPasswordField second;
	
	
	private File file= new File("LoginInfo.txt");
	private FileReader filereader = null;
	private BufferedReader bufReader;
	
	public SignUpScreen() {
		super("회원 가입 화면");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		add(stateScreen, BorderLayout.NORTH);
		stateScreen.setHorizontalAlignment(SwingConstants.CENTER);//글자 가운데 정렬
		stateScreen.setVerticalAlignment(SwingConstants.CENTER);
		
		try {
			filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
		} catch (FileNotFoundException e) {
			stateScreen.setText("LoginInfo.txt가 존재 하지 않습니다!!");
		}
		
		JPanel InputPanel = new JPanel();
		add(InputPanel,BorderLayout.CENTER);
		InputPanel.setLayout(new FlowLayout());
		JPanel IDField = new JPanel();
		InputPanel.add(IDField);
		IDField.setLayout(new FlowLayout());
		IDField.add(new JLabel("new ID"));
		IDinput = new JTextField(10);
		IDField.add(IDinput);
		JPanel PWField = new JPanel();
		InputPanel.add(PWField);
		PWField.setLayout(new GridLayout(2,1));
		JPanel firstPW = new JPanel();
		firstPW.setLayout(new FlowLayout());
		PWField.add(firstPW);
		firstPW.add(new JLabel("new PW"));
		PWinput = new JPasswordField(10);
		firstPW.add(PWinput);
		JPanel secondPW = new JPanel();
		secondPW.setLayout(new FlowLayout());
		PWField.add(secondPW);
		secondPW.add(new JLabel("PW check"));
		second = new JPasswordField(10);
		secondPW.add(second);
		
		apply = new JButton("회원 등록");
		apply.addActionListener(this);
		add(apply,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(String.valueOf(PWinput.getPassword()).equals(String.valueOf(second.getPassword())))
			if(String.valueOf(PWinput.getPassword()).equals("") || IDinput.getText().equals(""))
				stateScreen.setText("ID와 비밀번호는 공란이 될 수 없습니다");
			else if(IDinput.getText().contains(" ") || String.valueOf(PWinput.getPassword()).contains(" ")) {
				stateScreen.setText("ID와 비밀번호에는 공란이 올 수 없습니다.");
			}
			else {
				if(checkValidity(IDinput.getText(),String.valueOf(second.getPassword()))) {
					BufferedWriter bufferedWriter = null;
					try {
						bufferedWriter = new BufferedWriter(new FileWriter(file,true));
						bufferedWriter.append("\r\n"+LoginScreen.encrypt(IDinput.getText(), 5)+" "+LoginScreen.encrypt(String.valueOf(second.getPassword()), 5)+" false");
						bufferedWriter.close();
						stateScreen.setText("성공적으로 등록되었습니다");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					stateScreen.setText("이미 사용중인 ID 입니다. 다른 ID로 시도해 보십시오");
				}
			}
		else
			stateScreen.setText("비밀번호 확인과 비밀번호가 일치하지 않습니다");
		
	}
	private boolean checkValidity(String ID, String PW) {
		boolean isValid = true;
		String line,ReadID;
		try {
			while((line = bufReader.readLine())!=null) {
				ReadID = LoginScreen.encrypt(line.split(" ")[0],-5).toString();
				if(ReadID.equals(ID)) {
					isValid = false;
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
	}
}
