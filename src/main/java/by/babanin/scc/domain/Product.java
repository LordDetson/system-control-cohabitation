package by.babanin.scc.domain;

import by.babanin.scc.repository.converter.MoneyConverter;
import lombok.*;
import org.javamoney.moneta.Money;

import javax.money.format.MonetaryFormats;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Locale;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Byte quantity;

    @Convert(converter = MoneyConverter.class)
    private Money cost;
    private String owner;

    public String getFormattedCost(Locale locale) {
        return MonetaryFormats.getAmountFormat(locale).format(getCost());
    }
}
