package Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebColector {
	
	private List<String> vulnerabilitiesLinks;
	private List<String> pageLinks;
	
	public WebColector() {
		vulnerabilitiesLinks = new ArrayList<String>();
		pageLinks = new ArrayList<String>();
	}

	public List<String> getVulnerabilidadesLinks(int initialPage, int finalPage, String URL1, String URL2) throws IOException{
		vulnerabilitiesLinks.clear();

		for(int i = initialPage; i <= finalPage; i++){
			Connection connection = Jsoup.connect(URL1 + i + URL2);
			Document doc = connection.get();

			Pattern pattern = Pattern.compile("(http.*cvedetails.*CVE-[0-9]*-[0-9]*)");

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
	
	public List<String> getPageLinks(String URL, String regex) throws IOException{
		pageLinks.clear();

		Connection connection = Jsoup.connect(URL).timeout(300000).ignoreHttpErrors(true);
		Document doc = connection.get();

		Pattern pattern = Pattern.compile(regex);

		Elements links = doc.select("a[href]");

		for(Element l: links){
			String link = l.attr("abs:href");

			Matcher matcher = pattern.matcher(link);
			if(matcher.find()) {
				String result = matcher.group();
				pageLinks.add(result);
				//System.out.println("Result: " + result);

			}
		}

		return pageLinks;
	}
	
	public String getCode(String URL) throws IOException{
		String code = "";

		Connection connection = Jsoup.connect(URL).timeout(300000).ignoreHttpErrors(true);
		Document doc = connection.get();

		Elements ele = doc.getElementsByClass("vc_raw_diff");
        
        for (int i = 0; i < 1; i++) {
        	code = ele.get(i).getAllElements().get(0).text();
        }
		
		return code;
	}

}
