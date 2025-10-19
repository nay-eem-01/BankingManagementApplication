package org.example.bankingManagementApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.example.bankingManagementApplication.constatnt.AppTables;
import org.example.bankingManagementApplication.model.AuditModel;

import java.math.BigDecimal;

@Entity
@Table(name = AppTables.TRANSACTION_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions extends AuditModel<String> {

    @Column(name = AppTables.TransactionTable.TRANSACTION_ID, nullable = false)
    private String transactionId;

    @Column(name = AppTables.TransactionTable.FROM_ACCOUNT_NUMBER, nullable = false)
    private String fromAccountNumber;

    @Column(name = AppTables.TransactionTable.TO_ACCOUNT_NUMBER, nullable = false)
    private String toAccountNumber;

    @Column(name = AppTables.TransactionTable.TRANSACTION_AMOUNT, nullable = false)
    private BigDecimal amount;

}