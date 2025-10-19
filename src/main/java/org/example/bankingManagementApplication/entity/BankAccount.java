package org.example.bankingManagementApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.bankingManagementApplication.model.AuditModel;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends AuditModel<String> {

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}