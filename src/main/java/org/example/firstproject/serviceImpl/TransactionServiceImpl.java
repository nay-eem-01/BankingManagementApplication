package org.example.firstproject.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.entity.BankAccount;
import org.example.firstproject.entity.Transactions;
import org.example.firstproject.entity.User;
import org.example.firstproject.exceptionHandler.ResourceNotFoundException;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.response.TransactionResponse;
import org.example.firstproject.repository.BankAccountRepository;
import org.example.firstproject.repository.TransactionRepository;
import org.example.firstproject.service.TransactionService;
import org.example.firstproject.specification.TransactionSpecification;
import org.example.firstproject.utils.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AuthUtil authUtil;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AuthUtil authUtil, BankAccountRepository bankAccountRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.authUtil = authUtil;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<TransactionResponse> getAllTransactionsPaginated(PaginationArgs paginationArgs) {

        Sort sort = paginationArgs.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(paginationArgs.getSortBy()).ascending()
                : Sort.by(paginationArgs.getSortBy()).descending();

        Pageable pageable = PageRequest.of(paginationArgs.getPageNo(), paginationArgs.getPageSize(), sort);

        User loggedInUser = authUtil.getLoggedInUser();
        log.info("Logged in userId: {} userEmail: {} userName: {} ", loggedInUser.getId(), loggedInUser.getEmail(), loggedInUser.getFullName());

        BankAccount bankAccount = bankAccountRepository.findByUserId(loggedInUser.getId()).orElseThrow(() -> new ResourceNotFoundException("User Not found"));
        log.info("User Bank account number:{}", bankAccount.getAccountNumber());

        Specification<Transactions> specification = TransactionSpecification.getAllTransactionFromThisAccount(bankAccount.getAccountNumber());

        Page<Transactions> transactionsPage = transactionRepository.findAll(specification, pageable);
        return transactionsPage.map(transactions -> modelMapper.map(transactions, TransactionResponse.class));
    }
}
