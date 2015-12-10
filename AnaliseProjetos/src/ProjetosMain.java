import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProjetosMain {

	public static void main(String[] args) throws IOException {
		//fluxoChrome();
		fluxoHadoop();
	}
	
	public static void fluxoHadoop() throws IOException{
		Hadoop h = new Hadoop();
		
		ArrayList<String> vulnerabilidades = h.getVulnerabilidadesLinks();
		ArrayList<String> bugs;
		
		for(String vLink : vulnerabilidades){
			System.out.println("\nVulnerabilidade: " + vLink);
		}
	}
	
	public static void fluxoChrome() throws IOException{
		Chrome c = new Chrome();
		
		ArrayList<String> vulnerabilidades = c.getVulnerabilidadesLinks();
		ArrayList<String> bugs;
		ArrayList<String> bugs2;
		
		for(String vLink : vulnerabilidades){
			bugs = c.getBugsLinks(vLink);
			for(String bLink : bugs){
				bugs2 = c.getBugsLinks2(bLink);
				if(bugs2.size() != 0){
					System.out.println("\nVulnerabilidade: " + vLink.substring(30, 43));
				}
				for(String b2Link : bugs2){
					System.out.println("\t\tCode link: " + b2Link);
				}
			}
		}
	}

}
