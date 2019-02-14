package com.billme.billme.service.currency;

import com.billme.billme.jpa.currency.Currency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void shouldReturnCorrectCurrencyByCode() {
        Optional<Currency> currency = currencyService.getCurrencyByCode("ALL");
        Assert.assertTrue(currency.isPresent());
    }

    @Test
    public void shouldReturnEmptyCurrencyByCode() {
        Optional<Currency> currency = currencyService.getCurrencyByCode("NULL");
        Assert.assertTrue(!currency.isPresent());
    }
}
