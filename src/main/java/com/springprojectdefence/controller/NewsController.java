package com.springprojectdefence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping("/news")
public class NewsController {
    @GetMapping("carousel/1")
    public String viewCarouselFirstArticle()
    {
        return "news/carousel/news-1";
    }

    @GetMapping("carousel/2")
    public String viewCarouselSecondArticle()
    {
        return "news/carousel/news-2";
    }

    @GetMapping("carousel/3")
    public String viewCarouselThirdArticle()
    {
        return "news/carousel/news-3";
    }

    @GetMapping("header/1")
    public String viewHeaderFirstArticle()
    {
        return "news/header/left-header";
    }

    @GetMapping("header/2")
    public String viewHeaderSecondArticle()
    {
        return "news/header/middle-header";
    }

    @GetMapping("header/3")
    public String viewHeaderThirdArticle()
    {
        return "news/header/right-header";
    }
}
