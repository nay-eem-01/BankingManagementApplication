package org.example.firstproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginatedResponse<T> {
    private List<T> data;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;

}
