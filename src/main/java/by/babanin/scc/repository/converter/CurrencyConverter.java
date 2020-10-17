package by.babanin.scc.repository.converter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CurrencyConverter implements AttributeConverter<CurrencyUnit, String> {
    @Override
    public String convertToDatabaseColumn(CurrencyUnit currencyUnit) {
        return currencyUnit.getCurrencyCode();
    }

    @Override
    public CurrencyUnit convertToEntityAttribute(String currencyCode) {
        return Monetary.getCurrency(currencyCode);
    }
}
