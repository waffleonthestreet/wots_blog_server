package wots.blog.server.domain.dto;

import lombok.*;
import wots.blog.server.domain.Category;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CategoryListResponse {
    public Long categoryNo;

    public String categoryName;

    public Long prtCategoryNo;

    public boolean deltYn;

    public boolean publicYn;

    public int cLevel;

    public int cno;
}
