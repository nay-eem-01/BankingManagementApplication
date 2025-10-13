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
    public ResponseEntity<BankAccountResponse> CreateAccount(){
        BankAccountResponse bankAccountResponse = bankAccountService.createNewAccount();
        return new ResponseEntity<>(bankAccountResponse, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<BankAccountResponse> deposit(@RequestBody BankAccountRequest bankAccountRequest) {
        BankAccountResponse bankAccountResponse = bankAccountService.deposit(bankAccountRequest);
        return new ResponseEntity<>(bankAccountResponse, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transferFund(@RequestBody BalanceTransferRequest balanceTransferRequest){
        TransactionResponse transactionResponse = bankAccountService.transferFund(balanceTransferRequest);
        return new ResponseEntity<>(transactionResponse,HttpStatus.OK);
    }
    @GetMapping("/balance")
    public ResponseEntity<BankAccountResponse> viewBalance(){
        BankAccountResponse bankAccountResponse = bankAccountService.viewBalance();
        return new ResponseEntity<>(bankAccountResponse,HttpStatus.OK);
    }
}
