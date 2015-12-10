package Projects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Services.FileManagement;
import Services.WebColector;

public class TomCat {
	
	private static List<String> vulnerabilities;
	private static List<String> codes;
	private static List<String> links;
	private static WebColector wc;
	private static FileManagement fm;

	public static void main(String[] args) throws IOException {
		wc = new WebColector();
		fm = new FileManagement();
		
		vulnerabilities = fm.readFile("C:\\Users\\Gustavo\\Desktop\\Pesquisa\\Analise\\TomCat\\links.txt");
		
		for(String link : vulnerabilities){
			codes = new ArrayList<String>();
			links = wc.getPageLinks(link.split("	")[1], "(http.*svn.*viewvc.*tomcat.*r1.*pathrev.*)");
			System.out.println("\n" + link);
			for(String c : links){
				codes.add(wc.getCode(c));
			}
			fm.writeFile("C:\\Users\\Gustavo\\Desktop\\Pesquisa\\Analise\\TomCat\\" + link.split("	")[0] + ".txt", codes);
		}
	}
	
	public static void getCode() throws IOException{
		List<String> links = new ArrayList<String>();
		
		for(String v : vulnerabilities){
			System.out.println("\n" + v.substring(30, 43));
			links = wc.getPageLinks(v, "(http.*svn.*apache.*)");
			for(String l : links){
				System.out.println("\t" + l);
			}
		}
	}
	
	public static void saveVulnerabilities(){
		fm.writeFile("C:\\Users\\Gustavo\\Desktop\\Pesquisa\\TomCat\\vulnerabilities.txt", vulnerabilities);
	}

}
