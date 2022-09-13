package com.cyber.partizan.currencyconverter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rates")
public class Rate {

    public static final float rateMultiplier = 10000F;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "nominal", nullable = false)
    private Integer nominal = 1;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public static Rate getRubRate(LocalDate date) {
        var rubCurrency = Currency.getRubCurrency();
        var rate = new Rate();
        rate.setCurrency(rubCurrency);
        rate.setDate(date);
        rate.setNominal(1);
        rate.setValue(10000);
        return rate;
    }

}
