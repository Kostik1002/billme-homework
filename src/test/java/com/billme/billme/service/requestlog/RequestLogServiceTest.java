package com.billme.billme.service.requestlog;

import com.billme.billme.jpa.requestlog.RequestLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestLogServiceTest {

    @Autowired
    private RequestLogService requestLogService;

    @Test
    public void shouldSaveCorrectlyLogRequest() {
        requestLogService.save("USD",  "192.168.0.1");
        requestLogService.save("EUR",  "192.168.0.2");
        requestLogService.save("RUB",  "192.168.0.3");

        List<RequestLog> logs = requestLogService.getLogs();
        Assert.assertTrue(logs.size() == 3);
    }

    @Test(expected = NullPointerException.class)
    public void unableToLogIfRequestIsNull() {
        requestLogService.save(null,  "192.168.0.1");
    }

    @Test(expected = NullPointerException.class)
    public void unableToLogIfIpIsNull() {
        requestLogService.save("EUR",  null);
    }

    @Test(expected = NullPointerException.class)
    public void unableToLogIfRequestAndIpAreNull() {
        requestLogService.save(null,  null);
    }

}
