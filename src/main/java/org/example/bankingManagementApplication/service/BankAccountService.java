package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.model.request.BalanceTransferRequest;
import org.example.bankingManagementApplication.model.request.DepositRequest;
import org.example.bankingManagementApplication.model.response.BankAccountResponse;
import org.example.bankingManagementApplication.model.response.TransactionResponse;

public interface BankAccountService {
    BankAccountResponse createNewAccount();

    BankAccountResponse deposit(DepositRequest depositRequest);

    TransactionResponse transferFund(BalanceTransferRequest balanceTransferRequest);

    BankAccountResponse viewBalance();
}
