package org.example.firstproject.service;

import org.example.firstproject.model.request.BalanceTransferRequest;
import org.example.firstproject.model.request.BankAccountRequest;
import org.example.firstproject.model.response.BankAccountResponse;
import org.example.firstproject.model.response.TransactionResponse;

public interface BankAccountService  {
    BankAccountResponse createNewAccount();

    BankAccountResponse deposit(BankAccountRequest bankAccountRequest);

    TransactionResponse transferFund(BalanceTransferRequest balanceTransferRequest);

    BankAccountResponse viewBalance();
}
