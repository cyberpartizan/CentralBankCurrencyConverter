package com.cyber.partizan.currencyconverter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    @Column(name = "num_code", nullable = false)
    private String numCode;

    @Column(name = "charCode", nullable = false)
    private String charCode;

    public static Currency getRubCurrency(){
        var rub = new Currency();
        rub.setCharCode("RUB");
        rub.setId("R00000");
        rub.setName("Российский рубль");
        rub.setNumCode("643");
        return rub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return name.equals(currency.name) && numCode.equals(currency.numCode) && charCode.equals(currency.charCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numCode, charCode);
    }
}
