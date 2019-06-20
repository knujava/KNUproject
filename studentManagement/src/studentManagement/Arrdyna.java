//test & practice class(do not use)

package studentManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arrdyna {
	private static List<String> getBizList(String filePath){
		List<String> bizList = null;
		BufferedReader br = null;
		
		if (!(filePath == null)) {
			bizList = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(filePath));
				String s;
				
				while((s=br.readLine())!=null) {
					bizList.add(s);
				}
				br.close();
				
			}
			catch(IOException e) {
				System.err.println(e);
			}
			finally {
			try {if(br != null) {br.close();}}
			catch(Exception ex) {}	
			}
		}
		

		return bizList;
	}
}

