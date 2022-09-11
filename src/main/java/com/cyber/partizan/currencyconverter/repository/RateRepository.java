package com.cyber.partizan.currencyconverter.repository;

import com.cyber.partizan.currencyconverter.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

}
