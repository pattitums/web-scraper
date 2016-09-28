package com.ewise.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    public static final int QUERY_TIMEOUT = 5000;
    public static String extractLink(String link) {
        return link.substring("/url?q=".length(), link.indexOf("&"));
    }

    public List<String> getResults(String queryString) {
        List<String> results = new ArrayList<>();
        try {
            ScraperParameter param = new ScraperParameter(queryString);
            Document doc = Jsoup.connect(param.createGoogleRequest())
                    .userAgent(param.getUserAgent())
                    .timeout(QUERY_TIMEOUT).get();

            Elements links = doc.select("h3.r > a");
            for (Element link : links) {
                String linkHref = link.attr("href");
                String linkText = link.text();

                //If news exists, get the link of the first news as a search result
                if(linkText.contains("News for "+queryString)) {
                    Element td = doc.select("table.ts > tbody > tr > td > a").first();
                    if(td != null){
                        String tdHref = td.attr("href");
                        //System.out.println(extractLink(tdHref));
                        results.add(extractLink(tdHref));
                    }
                }
                if(linkHref.startsWith("/url?q=")) {
                    //System.out.println(extractLink(linkHref));
                    results.add(extractLink(linkHref));
                }
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return results;
    }
}

