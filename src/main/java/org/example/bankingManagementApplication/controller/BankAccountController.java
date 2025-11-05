package org.example.bankingManagementApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.config.CommonApiResponses;
import org.example.bankingManagementApplication.entity.BankAccount;
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
@CommonApiResponses
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Operation(summary = "Initialize bank account", security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = BankAccountResponse.class)), responseCode = "200")
    @PostMapping("/init")
    public ResponseEntity<HttpResponse> CreateAccount() {
        BankAccountResponse bankAccountResponse = bankAccountService.createNewAccount();
        return HttpResponse.getResponseEntity(HttpStatus.CREATED, "Account created successfully", bankAccountResponse, true);
    }

    @Operation(summary = "Deposit into account", security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = BankAccountResponse.class)), responseCode = "200")
    @PostMapping("/deposit")
    public ResponseEntity<HttpResponse> deposit(@RequestBody DepositRequest depositRequest) {
        BankAccountResponse bankAccountResponse = bankAccountService.deposit(depositRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Successfully deposited", bankAccountResponse, true);
    }

    @Operation(summary = "Transfer fund", security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = TransactionResponse.class)), responseCode = "200")
    @PostMapping("/transfer")
    public ResponseEntity<HttpResponse> transferFund(@RequestBody BalanceTransferRequest balanceTransferRequest) {
        TransactionResponse transactionResponse = bankAccountService.transferFund(balanceTransferRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Balance transferred successfully", transactionResponse, true);
    }

    @Operation(summary = "View balance", security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = BankAccountResponse.class)), responseCode = "200")
    @GetMapping("/balance")
    public ResponseEntity<HttpResponse> viewBalance() {
        BankAccountResponse bankAccountResponse = bankAccountService.viewBalance();
        return HttpResponse.getResponseEntity(HttpStatus.OK, "Remaining balance", bankAccountResponse, true);
    }
}
