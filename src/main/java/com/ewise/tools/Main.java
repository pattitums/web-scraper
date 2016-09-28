package com.ewise.tools;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class Main {
    public static void main(String[] args) {
//        staticFileLocation("/public");
        get("/", (rq, rs) -> new ModelAndView(null, "index.hbs"),
                new HandlebarsTemplateEngine());

        post("/results", (rq, rs) -> {
            Scraper googleScraper = new Scraper();
            List<String> results = googleScraper.getResults(rq.queryParams("querystring"));

            Map<String, Object> model = new HashMap<>();
            model.put("querystring",rq.queryParams("querystring"));
            model.put("results", results);
            return new ModelAndView(model, "results.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
