package wots.blog.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingResponse;
import wots.blog.server.service.AdminService;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5001/")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/test")
    public String test() {
        return "admin API reachable";
    }

    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<?> selectUsers () throws Exception {
        List<Account> results = adminService.SelectUsers();
        return ResponseEntity.ok(results);
    }

    @PostMapping("/user")
    public void insertAccount(@RequestBody Account account) {
        adminService.insertUser(account);
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<?> selectUserByIdAndPw(@RequestParam String id, @RequestParam String pw) {
        Account account = adminService.selectAccountByIdPw(LoginRequest.builder()
                .userId(id)
                .userPw(pw)
                .build());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/user/{no}")
    @ResponseBody
    public ResponseEntity<?> selectUserByUserNo(@PathVariable("no") Long userNo) {
        Account account = adminService.selectAccountByUserNo(userNo);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/article")
    public void saveArticle(@RequestBody Article article) {
        adminService.insertArticle(article);
    }

    @PutMapping("/article/{no}")
    public void updateArticle(@PathVariable("no") Long articleNo, @RequestBody Article article) {
        article.setArticleNo(articleNo);
        adminService.updateArticle(article);
    }

    @GetMapping("/article/{no}")
    @ResponseBody
    public ResponseEntity<?> getArticle(@PathVariable("no") Long articleNo) {
        Article article = adminService.selectArticle(articleNo);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<?> selectArticlesByPage(ArticleSearchRequest articleSearchRequest) {
        PagingResponse<Article> articles = adminService.selectArticlesByPage(articleSearchRequest);
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/upload/file")
    public ResponseEntity<?> uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(adminService.upload(multipartFile));
    }

    @DeleteMapping("/article/{no}")
    public void deleteArticle(@PathVariable("no") Long articleNo) {
        adminService.deleteArticle(articleNo);
    }
}
