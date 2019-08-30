package relipa.religram.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagingResponse<T> {
    private int totalPage;
    private List<T> list;

    public PagingResponse(int totalPage, List<T> list) {
        this.totalPage = totalPage;
        this.list = list;
    }
}
