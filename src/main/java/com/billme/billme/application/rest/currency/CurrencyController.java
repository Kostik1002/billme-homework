package com.billme.billme.application.rest.currency;

import com.billme.billme.service.currency.CurrencyService;
import com.billme.billme.service.requestlog.RequestLogService;
import com.billme.billme.jpa.currency.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    private final RequestLogService requestLogService;

    public CurrencyController(CurrencyService currencyService, RequestLogService requestLogService) {
        this.currencyService = currencyService;
        this.requestLogService = requestLogService;
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable(name = "code") String code, HttpServletRequest request) {
        requestLogService.save(code, request.getRemoteAddr());

        Optional<Currency> currency = currencyService.getCurrencyByCode(code);
        return currency.map(ResponseEntity::ok)
                .orElseThrow(CurrencyNotFoundException::new);
    }

}
