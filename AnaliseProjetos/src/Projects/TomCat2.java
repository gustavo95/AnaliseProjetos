package Projects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Services.FileManagement;
import Services.WebColector;

public class TomCat2 {
	private static List<String> vulnerabilities;
	private static List<String> codes;
	private static List<String> linkList;
	private static WebColector wc;
	private static FileManagement fm;

	public static void main(String[] args) throws IOException {
		fm = new FileManagement();
		wc = new WebColector();
		codes = new ArrayList<String>();
		vulnerabilities = fm.readFile("/home/anthony/links.txt");
		
		File dir = new File("/home/anthony/tomcat/");
		dir.mkdir();
		
		for (String linha : vulnerabilities) {
			String cve = linha.split(" ")[0];
			String links = linha.split(" ")[1];
			
			System.out.println(cve);

			// Cria os diretórios dos CVEs
			dir = new File("/home/anthony/tomcat/" + cve + "/");
			dir.mkdir();

			StringTokenizer st = new StringTokenizer(links);

			while (st.hasMoreTokens()) {
				//Vai para o próximo link
				String link = st.nextToken();
				
				//Obtém o número da revisão para salvar o arquivo
				String revision = link.replaceAll(".*?revision=", "");
				
				System.out.println("> " + revision);
								
					//Busca os links na página
					linkList = wc.getPageLinks(link, "(http.*svn.*viewvc.*tomcat.*r1.*pathrev.*)");

					for (String c : linkList) 
						codes.add(wc.getCode(c));

					fm.writeFile("/home/anthony/tomcat/" + cve + "/" + revision + ".diff", codes);					
				
			}

		}

	}

}

