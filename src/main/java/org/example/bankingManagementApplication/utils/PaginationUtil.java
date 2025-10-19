package org.example.bankingManagementApplication.utils;

import org.example.bankingManagementApplication.model.response.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public  class PaginationUtil {

    public <T>PaginatedResponse<T> buildingPaginatedResponse(Page<T> page){
        PaginatedResponse<T> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setData(page.getContent());
        paginatedResponse.setPageNo(page.getNumber());
        paginatedResponse.setTotalPage(page.getTotalPages());
        paginatedResponse.setTotalElement(page.getTotalElements());
        paginatedResponse.setPageSize(page.getSize());
        paginatedResponse.setHasNext(page.hasNext());
        paginatedResponse.setHasPrevious(page.hasPrevious());
        return paginatedResponse;
    }
}
