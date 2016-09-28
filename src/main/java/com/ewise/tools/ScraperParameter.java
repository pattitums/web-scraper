package com.ewise.tools;

public class ScraperParameter {

    //default number of results
    private int numResults = 10;
    //query to search
    private String query;
    //user agent for google crawler
    private String userAgent;

    /**
     * NOTE: List of common User Agents:
     * Chrome: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36
     * IE: Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko
     * Firefox: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0
     * Google Crawler: Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)
     */

    public Integer getNumResults() {
        return numResults;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getQuery() {
        return query;
    }

    public ScraperParameter(String query) {
        this.numResults = 10;
        this.query = query;
        this.userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    }

    public ScraperParameter(String query, int numResults, String userAgent) {
        this.numResults = numResults;
        this.query = query;
        this.userAgent = userAgent;
    }

    public String createRequest(String address) {
        return address+"?q="+getQuery();
    }

    public String createGoogleRequest() {
        return createRequest("https://www.google.com.ph/search")
                +"&num="+getNumResults().toString();
    }

}

