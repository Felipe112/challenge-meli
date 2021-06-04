package com.meli.challenge.repository;

import com.meli.challenge.domain.mutant.model.Being;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeingRepository extends JpaRepository<Being, Long> {

    Being findByDna(String dna);

    @Query("SELECT COUNT(b) as total FROM Being b WHERE b.mutant =:mutant ")
    Integer getStat(@Param("mutant") Boolean mutant);
}
