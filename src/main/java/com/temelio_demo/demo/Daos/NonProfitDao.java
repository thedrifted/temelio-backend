package com.temelio_demo.demo.Daos;

import com.temelio_demo.demo.Entities.NonProfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonProfitDao extends JpaRepository<NonProfit, Long> {
    NonProfit findNonProfitEntityByEmail(String email);
    NonProfit findNonProfitByNpid(String nonProfitId);
}
