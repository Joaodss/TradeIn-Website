package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
}
