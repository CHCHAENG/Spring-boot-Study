package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j // 로깅을 위한 annotation
public class ArticleController {

    @Autowired  // Spring boot가 미리 생성해놓은 객체를 가져다가 자동 연결해줌
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString()); --> 로깅 기능으로 대체
        log.info(form.toString());

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 data를 가져옴
        // id값을 통해서 찾은것, 해당 id 값이 없다면 null을 반환하는 것.
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        // 1. 모든 Article을 가져옴
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 view로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. view 페이지 설정
        return "articles/index";
    }
}
