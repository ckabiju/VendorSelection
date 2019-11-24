package com.ntt.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import  com.ntt.hibernate.Entity.StockDetails;
import java.util.Optional;

@Repository
public interface StockDetailsDao extends JpaRepository<StockDetails, Long> {

    @Query(value="SELECT * FROM stock_details  WHERE npi = ?1 LIMIT 1", nativeQuery = true)
    Optional<StockDetails> findFirstByOrderByNpiAsc(String npi);
}
