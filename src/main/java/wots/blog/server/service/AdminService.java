package wots.blog.server.service;

import org.springframework.web.multipart.MultipartFile;
import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingRequest;
import wots.blog.server.domain.dto.common.PagingResponse;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    public List<Account> SelectUsers() throws Exception;

    public void insertUser(Account account);

    public Account selectAccountByIdPw(LoginRequest account);

    public Account selectAccountByUserNo(Long userNo);

    public void insertArticle(Article article);

    public void updateArticle(Article article);

    public Article selectArticle(Long articleNo);

    public PagingResponse<Article> selectArticlesByPage(ArticleSearchRequest articleSearchRequest);

    public String upload(MultipartFile multipartFile) throws IOException;
}
