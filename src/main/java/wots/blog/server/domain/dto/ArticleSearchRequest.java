package wots.blog.server.domain.dto;

import lombok.*;
import wots.blog.server.domain.dto.common.PagingRequest;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ArticleSearchRequest extends PagingRequest {
    public Long categoryNo;

    public String keyword;
}
