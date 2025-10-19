package org.example.bankingManagementApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.bankingManagementApplication.constatnt.AppTables;
import org.example.bankingManagementApplication.model.AuditModel;

import java.math.BigDecimal;

@Entity
@Table(name = AppTables.BANK_ACCOUNT_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends AuditModel<String> {

    @Column(name = AppTables.BankAccountTable.ACCOUNT_NUMBER, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = AppTables.BankAccountTable.BALANCE, nullable = false)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = AppTables.UserTable.USER_ID, nullable = false, unique = true)
    private User user;

}