package org.example.firstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationArgs {
    private int pageNo;
    private int pageSize;
    private String sortBy;
    private String sortOrder;

}
