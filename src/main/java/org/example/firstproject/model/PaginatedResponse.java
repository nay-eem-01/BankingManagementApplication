package org.example.firstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse <T> {

    private List<T> data;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPage;
    private boolean hasNext;
    private boolean hasPrevious;

}
