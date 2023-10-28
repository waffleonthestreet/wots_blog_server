package wots.blog.server.domain.dto.common;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequest {
    public int page;

    public int pageSize;
}
