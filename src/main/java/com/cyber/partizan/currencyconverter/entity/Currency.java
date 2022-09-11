package com.cyber.partizan.currencyconverter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "currencies")
public class Currency {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nominal", nullable = false)
    private int nominal = 1;

    @Column(name = "num_code", nullable = false)
    private String numCode;

    @Column(name = "charCode", nullable = false)
    private String charCode;
}
