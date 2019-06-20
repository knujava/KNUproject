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
	private JLabel stateScreen = new JLabel("����� ID �� PW �Է�");
	private JTextField IDinput;
	private JPasswordField PWinput;
	private JButton apply;
	private JPasswordField second;
	
	
	private File file= new File("LoginInfo.txt");
	private FileReader filereader = null;
	private BufferedReader bufReader;
	
	public SignUpScreen() {
		super("ȸ�� ���� ȭ��");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		add(stateScreen, BorderLayout.NORTH);
		stateScreen.setHorizontalAlignment(SwingConstants.CENTER);//���� ��� ����
		stateScreen.setVerticalAlignment(SwingConstants.CENTER);
		
		try {
			filereader = new FileReader(file);
			bufReader = new BufferedReader(filereader);
		} catch (FileNotFoundException e) {
			stateScreen.setText("LoginInfo.txt�� ���� ���� �ʽ��ϴ�!!");
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
		
		apply = new JButton("ȸ�� ���");
		apply.addActionListener(this);
		add(apply,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(String.valueOf(PWinput.getPassword()).equals(String.valueOf(second.getPassword())))
			if(String.valueOf(PWinput.getPassword()).equals("") || IDinput.getText().equals(""))
				stateScreen.setText("ID�� ��й�ȣ�� ������ �� �� �����ϴ�");
			else if(IDinput.getText().contains(" ") || String.valueOf(PWinput.getPassword()).contains(" ")) {
				stateScreen.setText("ID�� ��й�ȣ���� ������ �� �� �����ϴ�.");
			}
			else {
				if(checkValidity(IDinput.getText(),String.valueOf(second.getPassword()))) {
					BufferedWriter bufferedWriter = null;
					try {
						bufferedWriter = new BufferedWriter(new FileWriter(file,true));
						bufferedWriter.append("\r\n"+LoginScreen.encrypt(IDinput.getText(), 5)+" "+LoginScreen.encrypt(String.valueOf(second.getPassword()), 5)+" false");
						bufferedWriter.close();
						stateScreen.setText("���������� ��ϵǾ����ϴ�");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					stateScreen.setText("�̹� ������� ID �Դϴ�. �ٸ� ID�� �õ��� ���ʽÿ�");
				}
			}
		else
			stateScreen.setText("��й�ȣ Ȯ�ΰ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
		
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
