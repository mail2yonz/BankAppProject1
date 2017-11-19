package com.example.demo.Repository;

import com.example.demo.Entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    Transaction findByAccNo(String  acc);

}
