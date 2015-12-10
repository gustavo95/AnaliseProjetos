import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Chrome {
	public ArrayList<String> getVulnerabilidadesLinks() throws IOException{
		ArrayList<String> vulnerabilitiesLinks = new ArrayList<String>();

		for(int i = 11; i <= 12; i++){
			Connection connection = Jsoup.connect("http://www.cvedetails.com/vulnerability-list.php?vendor_id=1224&product_id=15031&version_id=&page=" + i + "&hasexp=0&opdos=0&opec=0&opov=0&opcsrf=0&opgpriv=0&opsqli=0&opxss=0&opdirt=0&opmemc=0&ophttprs=0&opbyp=0&opfileinc=0&opginf=0&cvssscoremin=0&cvssscoremax=0&year=0&month=0&cweid=0&order=1&trc=1165&sha=dad84f9c3747d02e86132c2ca5dc09a296cdf556").timeout(300000).ignoreHttpErrors(true);
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

	public ArrayList<String> getBugsLinks(String url) throws IOException{
		ArrayList<String> bugsLinks = new ArrayList<String>();

		Connection connection = Jsoup.connect(url).timeout(300000).ignoreHttpErrors(true);
		Document doc = connection.get();

		Pattern pattern = Pattern.compile("(http.*code.*google.*)");

		Elements links = doc.select("a[href]");

		for(Element l: links){
			String link = l.attr("abs:href");

			Matcher matcher = pattern.matcher(link);
			if(matcher.find()) {
				String result = matcher.group();
				bugsLinks.add(result);
				//System.out.println("Bugs: " + result);

			}
		}

		return bugsLinks;
	}
	
	public ArrayList<String> getBugsLinks2(String url) throws IOException{
		ArrayList<String> bugsLinks = new ArrayList<String>();

		Connection connection = Jsoup.connect(url).timeout(300000).ignoreHttpErrors(true);
		Document doc = connection.get();

		Pattern pattern = Pattern.compile("((http.*codereview.*chromium.*)|(http.*trac.*webkit.*changeset.*))");
		//(http.*codereview.*chromium.*)|(http.*trac.*webkit.*changeset.*)

		Elements links = doc.select("a[href]");

		for(Element l: links){
			String link = l.attr("abs:href");

			Matcher matcher = pattern.matcher(link);
			if(matcher.find()) {
				String result = matcher.group();
				bugsLinks.add(result);
				//System.out.println("Bugs: " + result);

			}
		}

		return bugsLinks;
	}
}
