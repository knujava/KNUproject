package studentManagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ProfessorInfo {

	public static void main(String[] args) throws IOException {
		Random ran = new Random();
		PrintWriter outputStream = new PrintWriter(new FileOutputStream("src/ProfessorInfo.txt"));
		
		int IdNumber;
		char IdC,Pw;
		int i,j;
		for(i = 1;i<=10;i++) {
			for(j = 1;j<=3;j++) {
				IdC = (char)(ran.nextInt(26)+'a');
				outputStream.print(IdC);
			}
			IdNumber = ran.nextInt(1000);
			outputStream.print(IdNumber + " ");
			
			//비밀번호 8자리 임의 출력
			for(j = 1;j<= 8;j++) {
				Pw = (char)(ran.nextInt(89)+33);
				outputStream.print(Pw);
			}
			outputStream.print(" ");
			
			outputStream.println();
		}
		
		outputStream.close();
	}

}
