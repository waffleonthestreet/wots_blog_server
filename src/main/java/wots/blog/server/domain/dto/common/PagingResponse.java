package wots.blog.server.domain.dto.common;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PagingResponse<T> {
    public List<T> data;

    public int maxCnt;
}
