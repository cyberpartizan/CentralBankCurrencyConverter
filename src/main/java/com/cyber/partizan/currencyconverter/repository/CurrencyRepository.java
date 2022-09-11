package com.cyber.partizan.currencyconverter.repository;

import com.cyber.partizan.currencyconverter.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
