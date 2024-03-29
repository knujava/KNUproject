package studentManagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class LoginScreen extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;
	private JLabel stateScreen = new JLabel("학번을 입력하십시오");
	private JPasswordField PWinput;
	private JTextField IDinput;
	public LoginScreen() { 
		super("학적 관리 프로그램");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		File sourceimage = new File("logo.png");
		Image img = null;
		try {
			img = ImageIO.read(sourceimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picturePanel = new JLabel(new ImageIcon(img));
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		setLayout(new GridLayout(2,1));
		add(picturePanel);
		add(loginPanel);
		loginPanel.add(stateScreen, BorderLayout.NORTH);
		stateScreen.setHorizontalAlignment(SwingConstants.CENTER);//글자 가운데 정렬
		stateScreen.setVerticalAlignment(SwingConstants.CENTER);
		
		JPanel InputPanel = new JPanel();
		loginPanel.add(InputPanel,BorderLayout.CENTER);
		InputPanel.setLayout(new FlowLayout());
		JPanel IDField = new JPanel();
		InputPanel.add(IDField);
		IDField.setLayout(new FlowLayout());
		IDField.add(new JLabel("ID"));
		IDinput = new JTextField(10);
		IDField.add(IDinput);
		JPanel PWField = new JPanel();
		InputPanel.add(PWField);
		PWField.setLayout(new FlowLayout());
		PWField.add(new JLabel("PW"));
		PWinput = new JPasswordField(10);
		PWField.add(PWinput);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(this);
		InputPanel.add(LoginButton);
		
		JButton signup = new JButton("회원 가입");
		signup.addActionListener(this);
		loginPanel.add(signup,BorderLayout.SOUTH);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 13) {
			searchID(IDinput.getText(),String.valueOf(PWinput.getPassword()));
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 13) {
			searchID(IDinput.getText(),String.valueOf(PWinput.getPassword()));
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Login")
			searchID(IDinput.getText(),String.valueOf(PWinput.getPassword()));
		else {
			SignUpScreen signup = new SignUpScreen();
			signup.setVisible(true);
		}
		
	}
	private boolean searchID(String ID, String PW) {
		boolean isSuccess = false;
		boolean IDFound = false;
		File file = new File("LoginInfo.txt");
		FileReader filereader;
		filereader = null;
		String ReadID;
		String ReadPW;
		try {
			filereader = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("LoginInfo.txt file does not exist!");
			e.printStackTrace();
		}
		BufferedReader bufReader = new BufferedReader(filereader);
		String line = "";
		try {
			while((line = bufReader.readLine())!=null) {
				ReadID = encrypt(line.split(" ")[0],-5).toString();
				ReadPW = encrypt(line.split(" ")[1],-5).toString();
				boolean isProf = Boolean.parseBoolean(line.split(" ")[2]);
				if(ReadID.equals(ID)) {
					IDFound = true;
					if(ReadPW.equals(PW)) {
						isSuccess = true;
						if(isProf) {
							stateScreen.setText("Login Successful. hello professor");
							Mainform mainform = new Mainform();
							mainform.setVisible(true);
							dispose();
							break;
						}
							else {
							stateScreen.setText("Login Successful. hello student");
							AfterStudentLogin a = new AfterStudentLogin(IDinput.getText());
							a.setVisible(true);
							break;
							}
						}
					else {
						stateScreen.setText("PW가 맞지 않습니다");
					}
				}
			}
			if(!isSuccess&&!IDFound)
				stateScreen.setText("등록 되지 않은 ID 입니다");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			filereader.close();
			bufReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
    public static StringBuffer encrypt(String text, int s) 
    { 
        StringBuffer result= new StringBuffer(); 
  
        for (int i=0; i<text.length(); i++) 
        {  
            char ch = (char)(((int)text.charAt(i) + s)); 
                result.append(ch);  
        } 
        return result; 
    } 
}
