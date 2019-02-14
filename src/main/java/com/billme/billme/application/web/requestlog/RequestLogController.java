package com.billme.billme.application.web.requestlog;

import com.billme.billme.service.requestlog.RequestLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/logs")
public class RequestLogController {

    private final RequestLogService requestLogService;

    public RequestLogController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @GetMapping
    public String getLogs(Model model) {
        model.addAttribute("requestLogList", requestLogService.getLogs());
        return "requestLogList";
    }
}
