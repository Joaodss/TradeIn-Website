package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.TradeInRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeInRequestRepository extends JpaRepository<TradeInRequest, Long> {

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p")
    List<TradeInRequest> findAllJoined();

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p " +
            "WHERE t.email = :email")
    List<TradeInRequest> findByEmailJoined(String email);

    @Query("SELECT t FROM TradeInRequest t " +
            "LEFT JOIN FETCH t.products p " +
            "WHERE t.id = :id")
    Optional<TradeInRequest> findByIdJoined(long id);

}
