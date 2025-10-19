package org.example.bankingManagementApplication.specification;

import org.example.bankingManagementApplication.entity.Transactions;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecification {
    public static Specification<Transactions> getAllTransactionFromThisAccount(String fromAccountNumber){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("fromAccountNumber"),fromAccountNumber));

    }
    public static Specification<Transactions> getTransactionBetweenTwoAccounts(String fromAccountNumber, String toAccountNumber){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("fromAccountNumber"),fromAccountNumber),
                        criteriaBuilder.equal(root.get("toAccountNumber"),toAccountNumber)
                ));

    }

}
