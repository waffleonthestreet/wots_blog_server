package wots.blog.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.CategoryListResponse;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingResponse;
import wots.blog.server.service.BlogService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/categories")
    @ResponseBody
    public ResponseEntity<?> selectAllCategories() {
        List<CategoryListResponse> results = blogService.selectAllCategories();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/article/{no}")
    @ResponseBody
    public ResponseEntity<?> selectArticleByArticleNo(@PathVariable("no") Long articleNo) {
        Article article = blogService.selectArticleByArticleNo(articleNo);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/articles/popular")
    @ResponseBody
    public ResponseEntity<?> selectTopPopularArticles(@RequestParam int top) {
        List<Article> articles = blogService.selectTopPopularArticles(top);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/articles/latest")
    @ResponseBody
    public ResponseEntity<?> selectTopLatestArticles(@RequestParam int top) {
        List<Article> articles = blogService.selectTopLatestArticles(top);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<?> selectArticlesByCategoryPage(ArticleSearchRequest articleSearchRequest) {
        PagingResponse<Article> articles = blogService.selectArticlesByCategoryPage(articleSearchRequest);
        return ResponseEntity.ok(articles);
    }

    @PatchMapping("/article/{no}/view")
    public void increaseArticleViewCntBy1(@PathVariable("no") Long articleNo) {
        blogService.updateArticleViewCntPlus1(articleNo);
    }
}
