package com.pts.DAO;

import com.pts.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    public Payment getByTxnRef(String txnRef);
}
