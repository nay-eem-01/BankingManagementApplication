package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.model.PaginationArgs;
import org.example.bankingManagementApplication.model.response.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    Page<TransactionResponse> getAllTransactionsPaginated(PaginationArgs paginationArgs);
}
