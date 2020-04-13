package com.example.demosqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueSendingService {
    @Autowired
    protected JmsTemplate defaultJmsTemplate;

    public void sendResult(String result) {
        defaultJmsTemplate.convertAndSend("queue_results", result);
    }
}
