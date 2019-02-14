package com.billme.billme.service.requestlog;

import com.billme.billme.jpa.requestlog.RequestLog;
import com.billme.billme.jpa.requestlog.RequestLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class RequestLogService {

    private final RequestLogRepository requestLogRepository;

    public RequestLogService(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Transactional
    public void save(String request, String ip) {
        Objects.requireNonNull(request, "request is undefined");
        Objects.requireNonNull(ip, "ip is undefined");

        RequestLog log = createRequestLog(request, ip);
        requestLogRepository.save(log);
    }

    private RequestLog createRequestLog(String request, String ip) {
        RequestLog log = new RequestLog();
        log.setDate(LocalDateTime.now());
        log.setRequest(request);
        log.setIp(ip);
        return log;
    }

    @Transactional
    public List<RequestLog> getLogs() {
        return requestLogRepository.findAll();
    }

}
