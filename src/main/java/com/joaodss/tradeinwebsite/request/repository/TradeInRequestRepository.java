package com.joaodss.tradeinwebsite.request.repository;

import com.joaodss.tradeinwebsite.request.dao.TradeInRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TradeInRequestRepository extends JpaRepository<TradeInRequest, Long> {

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p")
    Set<TradeInRequest> findAllJoined();

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p " +
            "WHERE t.email = :email")
    Set<TradeInRequest> findByEmailJoined(String email);

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p " +
            "WHERE t.id = :id")
    Optional<TradeInRequest> findByIdJoined(long id);

}
