package org.example.firstproject.controller;

import org.example.firstproject.model.request.BalanceTransferRequest;
import org.example.firstproject.model.request.BankAccountRequest;
import org.example.firstproject.model.response.BankAccountResponse;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.model.response.TransactionResponse;
import org.example.firstproject.service.BankAccountService;
import org.example.firstproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankAccountController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @PostMapping("/init")
    public ResponseEntity<HttpResponse> CreateAccount() {
        BankAccountResponse bankAccountResponse = bankAccountService.createNewAccount();
        return HttpResponse.getResponseEntity(HttpStatus.CREATED, "Account created successfully", bankAccountResponse, true);
    }

    @PostMapping("/deposit")
    public ResponseEntity<HttpResponse> deposit(@RequestBody BankAccountRequest bankAccountRequest) {
        BankAccountResponse bankAccountResponse = bankAccountService.deposit(bankAccountRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Successfully deposited", bankAccountResponse, true);
    }

    @PostMapping("/transfer")
    public ResponseEntity<HttpResponse> transferFund(@RequestBody BalanceTransferRequest balanceTransferRequest) {
        TransactionResponse transactionResponse = bankAccountService.transferFund(balanceTransferRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Balance transferred successfully", transactionResponse, true);
    }

    @GetMapping("/balance")
    public ResponseEntity<HttpResponse> viewBalance() {
        BankAccountResponse bankAccountResponse = bankAccountService.viewBalance();
        return HttpResponse.getResponseEntity(HttpStatus.OK, bankAccountResponse);
    }
}
