package org.example.bankingManagementApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.model.PaginationArgs;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.model.response.TransactionResponse;
import org.example.bankingManagementApplication.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.example.bankingManagementApplication.constatnt.AppConstants.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<HttpResponse> getAllTransactions(
            @RequestParam(name = PAGE_NO, defaultValue = "0") int pageNo,
            @RequestParam(name = PAGE_SIZE, defaultValue = "10") int pageSize,
            @RequestParam(name = SORT_BY, defaultValue = "id") String sortBy,
            @RequestParam(name = SORT_ORDER, defaultValue = "asc") String sortOrder) {

        PaginationArgs paginationArgs = new PaginationArgs(pageNo, pageSize, sortBy, sortOrder);
        Page<TransactionResponse> transactionsPaginated = transactionService.getAllTransactionsPaginated(paginationArgs);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Transactions loaded successfully", transactionsPaginated, true);
    }
}
