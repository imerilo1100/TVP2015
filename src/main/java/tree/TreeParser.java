package tree;

import java.io.BufferedReader;
import java.io.FileReader;

public class TreeParser {

	public static Tree parseTree(String filename) {
        String filecontent = "";
		try {
			String line;		
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            while((line = bufferedReader.readLine())!=null) {filecontent += line;}   
            bufferedReader.close();         
        }catch(Exception e) {return null;}
		//String will contain entire tree now. 
		System.out.println(filecontent); 
		return null; 
	}
}
