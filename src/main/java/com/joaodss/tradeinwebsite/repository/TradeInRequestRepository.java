package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeInRequestRepository extends JpaRepository<TradeInRequest, Long> {



}
