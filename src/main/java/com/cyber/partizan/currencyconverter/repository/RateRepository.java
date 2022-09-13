package com.cyber.partizan.currencyconverter.repository;

import com.cyber.partizan.currencyconverter.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    Optional<Rate> findByCurrencyIdAndDate(String id, LocalDate date);

    List<Rate> findByDate(LocalDate date);


}
