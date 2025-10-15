package org.example.firstproject.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.constatnt.AppConstants;
import org.example.firstproject.entity.BankAccount;
import org.example.firstproject.entity.Transactions;
import org.example.firstproject.entity.User;
import org.example.firstproject.exceptionHandler.AccountAlreadyExistsExceptionHandler;
import org.example.firstproject.exceptionHandler.ResourceNotFoundException;
import org.example.firstproject.exceptionHandler.TransactionExceptionHandler;
import org.example.firstproject.model.request.BalanceTransferRequest;
import org.example.firstproject.model.request.BankAccountRequest;
import org.example.firstproject.model.response.BankAccountResponse;
import org.example.firstproject.model.response.TransactionResponse;
import org.example.firstproject.repository.BankAccountRepository;
import org.example.firstproject.repository.TransactionRepository;
import org.example.firstproject.repository.UserRepository;
import org.example.firstproject.service.BankAccountService;
import org.example.firstproject.utils.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository;


    public BankAccountServiceImpl(UserRepository userRepository, AuthUtil authUtil, BankAccountRepository bankAccountRepository, ModelMapper modelMapper, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.authUtil = authUtil;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public BankAccountResponse createNewAccount() {
        User loggedInUser = authUtil.getLoggedInUser();
        if (bankAccountRepository.findByUserId(loggedInUser.getId()).isPresent()) {
            log.error("User already exists with email: {}", loggedInUser.getEmail());
            throw new AccountAlreadyExistsExceptionHandler("User already have account");
        }

        BankAccount bankAccount = new BankAccount();
        String userId = loggedInUser.getId().toString();
        String accountNumber = "ACC" + userId.substring(0, 3);
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setBalance(AppConstants.DefaultBalance);
        bankAccount.setUser(loggedInUser);

        log.info("Account created\nAccount no.: {} \nBalance: {}", bankAccount.getAccountNumber(), bankAccount.getBalance());

        return modelMapper.map(bankAccountRepository.save(bankAccount), BankAccountResponse.class);
    }

    @Override
    public BankAccountResponse deposit(BankAccountRequest bankAccountRequest) {

        User loggedInUser = authUtil.getLoggedInUser();
        String accountNumber = bankAccountRequest.getAccountNumber();

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);

        if (bankAccount.getUser() != loggedInUser) {
            throw new TransactionExceptionHandler("Account number does not matches");
        }
        if (bankAccountRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Deposit amount is O or less than 0: {}", bankAccountRequest.getAmount());
            throw new TransactionExceptionHandler("Amount should be more than 0.00");
        }
        bankAccount.setBalance(bankAccount.getBalance().add(bankAccountRequest.getAmount()));
        return modelMapper.map(bankAccountRepository.save(bankAccount), BankAccountResponse.class);
    }

    @Transactional
    @Override
    public TransactionResponse transferFund(BalanceTransferRequest balanceTransferRequest) {

        if (balanceTransferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Transfer amount is 0 or less than 0: {}", balanceTransferRequest.getAmount());
            throw new TransactionExceptionHandler("Amount should be more than 0.00");
        }
        User loggedInUser = authUtil.getLoggedInUser();

        String toAccountNumber = balanceTransferRequest.getToAccountNumber();
        BankAccount receiverAccount = bankAccountRepository.findByAccountNumber(toAccountNumber);

        if (!userRepository.existsUserByEmail(receiverAccount.getUser().getEmail())) {
            log.error("Receiver account does not exist: {}", receiverAccount);
            throw new ResourceNotFoundException("Receiver Account does not exist");
        }

        BankAccount senderAccount = bankAccountRepository
                .findByUserId(loggedInUser.getId()).orElseThrow(() -> new RuntimeException("Sender account does not exist"));

        log.info("Before transaction\nSender Account balance: {}", senderAccount.getBalance());

        if (senderAccount.getBalance().compareTo(balanceTransferRequest.getAmount()) < 0) {
            log.error("Sender account has insufficient transfer amount: {}", senderAccount.getBalance());
            throw new TransactionExceptionHandler("Insufficient balance");
        }

        receiverAccount.setBalance(receiverAccount.getBalance().add(balanceTransferRequest.getAmount()));
        bankAccountRepository.save(receiverAccount);
        senderAccount.setBalance(senderAccount.getBalance().subtract(balanceTransferRequest.getAmount()));
        bankAccountRepository.save(senderAccount);
        log.info("After transaction\nSender Account balance: {}", senderAccount.getBalance());

        String transactionId = UUID.randomUUID().toString();
        Transactions transactions = new Transactions();
        transactions.setTransactionId(transactionId);
        transactions.setToAccountNumber(receiverAccount.getAccountNumber());
        transactions.setFromAccountNumber(senderAccount.getAccountNumber());
        transactions.setAmount(balanceTransferRequest.getAmount());

        log.info("Sender account:{}", senderAccount.getAccountNumber());
        log.info("Receiver account:{}", receiverAccount.getAccountNumber());
        log.info("Transaction id: {}", transactionId);


        return modelMapper.map(transactionRepository.save(transactions), TransactionResponse.class);
    }

    @Override
    public BankAccountResponse viewBalance() {
        User loggedInUser = authUtil.getLoggedInUser();
        BankAccount bankAccount = bankAccountRepository
                .findByUserId(loggedInUser.getId()).orElseThrow(() -> new ResourceNotFoundException("Sender account does not exist"));
        log.info("Balance: {}", bankAccount.getBalance());
        return new BankAccountResponse(bankAccount.getAccountNumber(), bankAccount.getBalance());
    }


}
