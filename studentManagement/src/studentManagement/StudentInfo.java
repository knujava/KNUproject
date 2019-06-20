package studentManagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
public class StudentInfo {
	public static void main(String[] args) throws IOException{
		Random ran = new Random();
		int StudentNumber_part,StudentNumber_year; // StudentNumber_part 가 학번의 년도 뒤에 6자리, StudentNumber_year 가 학번의 년도 4자리
		int IdNumber, Grade,i,j,CourseCredit;
		float score;
		boolean isStudentOrProfessor;
		char a;
		PrintWriter outputStream = new PrintWriter(new FileOutputStream("src/StudentInfo.txt")); 
		
		outputStream.println("아이디 비밀번호 학번 학년 평균학점 이수학점");
		for(i = 1;i<=60;i++) {
			//아이디 임의출력
			for(j = 1;j<=3;j++){
				a = (char)(ran.nextInt(26)+97);
				outputStream.print(a);
			}
			IdNumber = ran.nextInt(10000);
			outputStream.print(IdNumber + " ");
			
			//비밀번호 8자리 임의 출력
			for(j = 1;j<= 8;j++) {
				a = (char)(ran.nextInt(89)+33);
				outputStream.print(a);
			}
			outputStream.print(" ");
			
			//학번 임의출력
			StudentNumber_year = ran.nextInt(6)+2014;
			outputStream.print(StudentNumber_year);
			for(j = 1;j<=6;j++) {
				StudentNumber_part = ran.nextInt(10);
				outputStream.print(StudentNumber_part);
			}
			outputStream.print(" ");
			
			//학년 랜덤 출력
			Grade = 2020-(StudentNumber_year);
			if(Grade > 4) {
				outputStream.print(4 + " ");
			}
			else {
				outputStream.print(Grade + " ");
			}
			
			//평균 학점랜덤 출력
			score = (float)ran.nextInt(430)/100;
			outputStream.printf("%.2f ",score);
			
			CourseCredit = ran.nextInt(23)+2;
			outputStream.print(CourseCredit + " ");
			
			outputStream.println();
		}
		outputStream.close();
	}
}
