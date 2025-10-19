package org.example.bankingManagementApplication.repository;

import org.example.bankingManagementApplication.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long>, JpaSpecificationExecutor<Transactions> {

}
