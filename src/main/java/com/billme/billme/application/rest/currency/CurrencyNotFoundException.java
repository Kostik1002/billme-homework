package com.billme.billme.application.rest.currency;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Currency not found")
class CurrencyNotFoundException extends RuntimeException {
}
