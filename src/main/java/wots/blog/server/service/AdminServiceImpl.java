package wots.blog.server.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingResponse;
import wots.blog.server.mapper.AdminMapper;
import wots.blog.server.mapper.BlogMapper;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Account> SelectUsers() throws Exception {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        List<Account> results = adminMapper.SelectAllAccounts();
        return results;
    }

    @Override
    public void insertUser(Account account) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.insertAccount(account);
    }

    @Override
    public Account selectAccountByIdPw(LoginRequest loginRequest) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Account account = adminMapper.selectAccountByIdPw(loginRequest);
        return account;
    }

    @Override
    public Account selectAccountByUserNo(Long userNo) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Account account = adminMapper.selectAccountByUserNo(userNo);
        return account;
    }

    @Override
    public void insertArticle(Article article) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.updateArticle(article);
    }

    @Override
    public Article selectArticle(Long articleNo) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Article article = adminMapper.selectArticle(articleNo);
        return article;
    }

    @Override
    public PagingResponse<Article> selectArticlesByPage(ArticleSearchRequest articleSearchRequest) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        PagingResponse<Article> results = new PagingResponse<Article>();

        articleSearchRequest.setPage((articleSearchRequest.page - 1) * articleSearchRequest.pageSize);

        List<Article> articles = adminMapper.selectArticlesByPage_Data(articleSearchRequest);
        results.setData(articles);

        int maxCnt = adminMapper.selectArticlesByPage_Count(articleSearchRequest);
        results.setMaxCnt(maxCnt);

        return results;
    }
}
