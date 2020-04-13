package com.example.demosqs.service;

import com.example.demosqs.config.AppProperties;
import com.example.demosqs.exception.MessageProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QueueReadingService {

    public static final String MESSAGE_CANNOT_BE_PROCESSED = "error";

    @Autowired
    private QueueSendingService sendingService;

    @Autowired
    private AppProperties properties;

    @JmsListener(destination = "queue_requests")
    public void receiveRequest(String requestJSON) throws InterruptedException {
        log.info(requestJSON);
        String result = process(requestJSON);
        sendingService.sendResult(result);
    }

    private String process(String requestJSON) throws InterruptedException {
        simulateProcessing();
        simulateError(requestJSON);
        return "Received " + requestJSON;
    }

    private void simulateProcessing() throws InterruptedException {
        Thread.sleep(properties.getDurationSimulating());
    }

    private void simulateError(String requestJSON) {
        if (requestJSON.equals(MESSAGE_CANNOT_BE_PROCESSED)) {
            throw new MessageProcessingException("Message cannot processed");
        }
    }
}
