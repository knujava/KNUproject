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
		
		String header[] = {"�й�","�̸�","�����","����"};
		String contents[][] = {
				{"2016000000", "��ö��", "�ɸ���", "4.3"},
				{"2015000000", "��¯��", "�ڹ�", "0.7"},
				{"2017000000", "������", "�ڷᱸ��", "3.3"},
				{"2018000000", "�ڸͱ�", "�Ȱ���", "1.7"},
				{"2019000000", "����", "�ʱޱ��翵��", "2.7"}
		};
		
		
		JTable table = new JTable(contents, header);
		JScrollPane Scrollpane = new JScrollPane(table);
		add(Scrollpane);
		pack();//
		
		JButton actionButton = new JButton("hid");
		actionButton.addActionListener(this);//
		add(actionButton);
	}
	//ȭ�鱸���ϱ�
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if (buttonString.contentEquals("")) {
			
			
		}
		
		
	}
}
