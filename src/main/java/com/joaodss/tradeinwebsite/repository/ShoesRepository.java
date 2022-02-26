package com.joaodss.tradeinwebsite.repository;

import com.joaodss.tradeinwebsite.dao.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes,Long> {
}
