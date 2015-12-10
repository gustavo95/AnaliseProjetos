package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {

	public List<String> readFile(String name){
		List<String> links = new ArrayList<String>();
		
		try {
			FileReader reader = new FileReader(name);
			BufferedReader leitor = new BufferedReader(reader);
			String linha = null;  
			while(leitor.ready()) { 
				linha = leitor.readLine();
			    links.add(linha);
			}
			leitor.close();  
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return links;
	}
	
	public void writeFile(String name, List<String> content){
		try{
			File newFile = new File(name);
			FileWriter writer = new FileWriter(newFile);
			PrintWriter out = new PrintWriter(writer);
			for(String c : content){
				System.out.println(c);
				out.println(c);
				//out.println(c.substring(30, 43));
			}
			out.close();
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
