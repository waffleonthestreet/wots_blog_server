package wots.blog.server.mapper;

import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.LoginRequest;

import java.util.List;

public interface AdminMapper {
    public List<Account> SelectAllAccounts();

    public void insertAccount (Account account);

    public Account selectAccountByIdPw(LoginRequest loginRequest);

    public Account selectAccountByUserNo(Long userNo);

    public void insertArticle(Article article);

    public void  updateArticle(Article article);

    public Article selectArticle(Long articleNo);

    public List<Article> selectArticlesByPage_Data(ArticleSearchRequest articleSearchRequest);

    public int selectArticlesByPage_Count(ArticleSearchRequest articleSearchRequest);
}
