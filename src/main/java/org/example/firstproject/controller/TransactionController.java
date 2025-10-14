package org.example.firstproject.controller;

import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.model.response.PaginatedResponse;
import org.example.firstproject.model.response.TransactionResponse;
import org.example.firstproject.service.TransactionService;
import org.example.firstproject.utils.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.example.firstproject.constatnt.AppConstants.*;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {
    private final TransactionService transactionService;
    private final PaginationUtil paginationUtil;


    public TransactionController(TransactionService transactionService, PaginationUtil paginationUtil) {
        this.transactionService = transactionService;
        this.paginationUtil = paginationUtil;
    }


    @GetMapping("/transactions")
    public ResponseEntity<HttpResponse> getAllTransactions(
            @RequestParam(name = PAGE_NO, defaultValue = "0") int pageNo,
            @RequestParam(name = PAGE_SIZE, defaultValue = "10") int pageSize,
            @RequestParam(name = SORT_BY, defaultValue = "id") String sortBy,
            @RequestParam(name = SORT_ORDER, defaultValue = "asc") String sortOrder) {

        PaginationArgs paginationArgs = new PaginationArgs(pageNo, pageSize, sortBy, sortOrder);
        Page<TransactionResponse> transactionResponses = transactionService.getAllTransactionsPaginated(paginationArgs);
        PaginatedResponse<TransactionResponse> paginatedResponse = paginationUtil.buildingPaginatedResponse(transactionResponses);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Transactions loaded successfully", paginatedResponse, true);
    }
}
