package org.example.firstproject.service;

import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.response.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    Page<TransactionResponse> getAllTransactionsPaginated(PaginationArgs paginationArgs);
}
