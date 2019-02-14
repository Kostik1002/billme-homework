package com.billme.billme;

import com.billme.billme.jpa.currency.Currency;
import com.billme.billme.jpa.currency.CurrencyRepository;
import com.billme.billme.service.wiki.WikiActiveCodeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAutoConfiguration
public class BillmeApplication {

    private final WikiActiveCodeService activeCodeProvider;
    private final CurrencyRepository currencyRepository;

    public BillmeApplication(WikiActiveCodeService activeCodeProvider, CurrencyRepository currencyRepository) {
        this.activeCodeProvider = activeCodeProvider;
        this.currencyRepository = currencyRepository;
    }

    @PostConstruct
    public void init() {
        List<Currency> currencies = activeCodeProvider.getActiveCodes().stream()
                .map(activeCode -> {
                    Currency currency = new Currency();
                    currency.setCode(activeCode.getCode());
                    currency.setNumber(activeCode.getNumber());
                    currency.setScaling(activeCode.getScaling());
                    currency.setTitle(activeCode.getTitle());

                    return currency;
                })
                .collect(Collectors.toList());

        currencyRepository.saveAll(currencies);
    }

    public static void main(String[] args) {
        SpringApplication.run(BillmeApplication.class, args);
    }

}

