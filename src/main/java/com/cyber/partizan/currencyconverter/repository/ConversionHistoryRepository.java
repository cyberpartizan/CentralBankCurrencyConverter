package com.cyber.partizan.currencyconverter.repository;

import com.cyber.partizan.currencyconverter.entity.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    @Query("SELECT ch FROM ConversionHistory ch " +
            "WHERE ch.date = :date " +
            "AND ch.sourceCurrency.id = :sourceId " +
            "AND ch.targetCurrency.id = :targetId ")
    List<ConversionHistory> findHistory(LocalDate date, String sourceId, String targetId);

}
