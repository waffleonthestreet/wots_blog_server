package wots.blog.server.service;

import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.CategoryListResponse;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingResponse;

import java.util.List;

public interface BlogService {

    public List<CategoryListResponse> selectAllCategories();

    public Article selectArticleByArticleNo(Long articleNo);

    public List<Article> selectTopPopularArticles(int top);

    public List<Article> selectTopLatestArticles(int top);

    public PagingResponse<Article> selectArticlesByCategoryPage(ArticleSearchRequest articleSearchRequest);

    public void updateArticleViewCntPlus1(Long articleNo);
}
