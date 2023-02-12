package com.pts.DAO;

import com.pts.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByTransactionNo(String transactionNo);
}
