package wots.blog.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.CategoryListResponse;
import wots.blog.server.domain.dto.common.PagingResponse;
import wots.blog.server.mapper.BlogMapper;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<CategoryListResponse> selectAllCategories() {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<CategoryListResponse> results = blogMapper.selectAllCategories();
        return results;
    }

    @Override
    public Article selectArticleByArticleNo(Long articleNo) {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Article article = blogMapper.selectArticleByArticleNo(articleNo);
        return article;
    }

    @Override
    public List<Article> selectTopPopularArticles(int top) {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Article> articles = blogMapper.selectTopPopularArticles(top);
        return articles;
    }

    @Override
    public List<Article> selectTopLatestArticles(int top) {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        List<Article> articles = blogMapper.selectTopLatestArticles(top);
        return null;
    }

    @Override
    public PagingResponse<Article> selectArticlesByCategoryPage(ArticleSearchRequest articleSearchRequest) {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        PagingResponse<Article> results = new PagingResponse<Article>();
        List<Article> articles = blogMapper.selectArticlesByCategoryPage_Data(articleSearchRequest);
        int maxCnt = blogMapper.selectArticlesByCategoryPage_Count(articleSearchRequest);

        results.setData(articles);
        results.setMaxCnt(maxCnt);

        return results;
    }

    @Override
    public void updateArticleViewCntPlus1(Long articleNo) {
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.updateArticleViewCntPlus1(articleNo);
    }
}
