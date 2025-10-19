package org.example.bankingManagementApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.model.request.BalanceTransferRequest;
import org.example.bankingManagementApplication.model.request.DepositRequest;
import org.example.bankingManagementApplication.model.response.BankAccountResponse;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.model.response.TransactionResponse;
import org.example.bankingManagementApplication.service.BankAccountService;
import org.example.bankingManagementApplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/init")
    public ResponseEntity<HttpResponse> CreateAccount() {
        BankAccountResponse bankAccountResponse = bankAccountService.createNewAccount();
        return HttpResponse.getResponseEntity(HttpStatus.CREATED, "Account created successfully", bankAccountResponse, true);
    }

    @PostMapping("/deposit")
    public ResponseEntity<HttpResponse> deposit(@RequestBody DepositRequest depositRequest) {
        BankAccountResponse bankAccountResponse = bankAccountService.deposit(depositRequest);
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
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Remaining balance", bankAccountResponse, true);
    }
}
