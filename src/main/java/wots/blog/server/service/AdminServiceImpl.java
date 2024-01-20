package wots.blog.server.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wots.blog.server.domain.Account;
import wots.blog.server.domain.Article;
import wots.blog.server.domain.dto.ArticleSearchRequest;
import wots.blog.server.domain.dto.LoginRequest;
import wots.blog.server.domain.dto.common.PagingResponse;
import wots.blog.server.mapper.AdminMapper;
import wots.blog.server.mapper.BlogMapper;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SqlSession sqlSession;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

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

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    @Override
    public void deleteArticle(Long articleNo) {
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.deleteArticle(articleNo);
    }
}
