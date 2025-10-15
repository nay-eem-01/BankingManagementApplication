package org.example.firstproject.service;

import org.example.firstproject.model.request.BalanceTransferRequest;
import org.example.firstproject.model.request.DepositRequest;
import org.example.firstproject.model.response.BankAccountResponse;
import org.example.firstproject.model.response.TransactionResponse;

public interface BankAccountService {
    BankAccountResponse createNewAccount();

    BankAccountResponse deposit(DepositRequest depositRequest);

    TransactionResponse transferFund(BalanceTransferRequest balanceTransferRequest);

    BankAccountResponse viewBalance();
}
