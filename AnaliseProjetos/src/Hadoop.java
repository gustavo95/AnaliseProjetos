import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Hadoop {
	
	public ArrayList<String> getVulnerabilidadesLinks() throws IOException{
		ArrayList<String> vulnerabilitiesLinks = new ArrayList<String>();

		for(int i = 1; i <= 1; i++){
			Connection connection = Jsoup.connect("https://issues.apache.org/jira/issues/?jql=project%20%3D%20HADOOP%20AND%20issuetype%20%3D%20Bug%20ORDER%20BY%20priority%20DESC").timeout(300000).ignoreHttpErrors(true);
			Document doc = connection.get();

			Pattern pattern = Pattern.compile("((http.*jira.*HADOOP-.*))");

			Elements links = doc.select("a[href]");

			for(Element l: links){
				String link = l.attr("abs:href");

				Matcher matcher = pattern.matcher(link);
				if(matcher.find()) {
					String result = matcher.group();
					vulnerabilitiesLinks.add(result);
					//System.out.println("Vulnerabilidade: " + result);

				}
			}
		}

		return vulnerabilitiesLinks;
	}
	
}
