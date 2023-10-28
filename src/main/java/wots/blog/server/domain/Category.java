package wots.blog.server.domain;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Category {
    public Long categoryNo;

    public String categoryName;

    public Long prtCategoryNo;

    public boolean deltYn;

    public boolean publicYn;
}
