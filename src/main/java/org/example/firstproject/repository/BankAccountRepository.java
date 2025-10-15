package org.example.firstproject.repository;

import org.example.firstproject.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);

    Optional<BankAccount> findByUserId(Long userId);

    Boolean existsByUserId(Long userId);
}
