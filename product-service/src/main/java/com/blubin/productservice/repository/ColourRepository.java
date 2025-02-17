package com.blubin.productservice.repository;

import com.blubin.productservice.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends JpaRepository<Colour, Long> {
    @Query("select e from Colour e where e.hexCode = ?1 and (?2 is null or e.id != ?2)")
    Colour findExistedHexCode(String hexCode, Long id);
}
