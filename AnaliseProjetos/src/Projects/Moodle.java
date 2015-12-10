package Projects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Services.FileManagement;
import Services.WebColector;

public class Moodle {
	
	private static List<String> vulnerabilities;
	private static List<String> links;
	private static WebColector wc;
	private static FileManagement fm;

	public static void main(String[] args) throws IOException {
		wc = new WebColector();
		fm = new FileManagement();
		
		vulnerabilities = wc.getVulnerabilidadesLinks(1, 6, 
				"http://www.cvedetails.com/vulnerability-list.php?vendor_id=2105&product_id=3590&version_id=&page=", 
				"&hasexp=0&opdos=0&opec=0&opov=0&opcsrf=0&opgpriv=0&opsqli=0&opxss=0&opdirt=0&opmemc=0&ophttprs=0&opbyp=0&opfileinc=0&opginf=0&cvssscoremin=0&cvssscoremax=0&year=0&month=0&cweid=0&order=1&trc=281&sha=9ddd526d6c3de20c88ac1e41f0bc477f1393cc17");
		
		links = new ArrayList<String>();
		getLinks();
		saveLinks();
		
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
		fm.writeFile("C:\\Users\\Gustavo\\Desktop\\Pesquisa\\Analise\\Moodle\\vulnerabilities.txt", vulnerabilities);
	}
	
	public static void saveLinks(){
		fm.writeFile("C:\\Users\\Gustavo\\Desktop\\Pesquisa\\Analise\\Moodle\\links.txt", links);
	}
	
	public static void getLinks() throws IOException{
		List<String> aux;
		
		for(String v : vulnerabilities){
			System.out.println("\n" + v);
			aux = wc.getPageLinks(v, "(http.*git\\.moodle.*commit.*)");
			if(aux.size() == 0){
				links.add("null");
			}else{
				links.add(aux.get(0));
			}
		}
	}

}
