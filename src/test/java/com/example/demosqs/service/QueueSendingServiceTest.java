package com.example.demosqs.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class QueueSendingServiceTest {

    @InjectMocks
    private QueueSendingService sendingService;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    public void successMessage() throws InterruptedException {
        String message = "message";
        sendingService.sendResult(message);

        ArgumentCaptor<String> argumentQueue = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentMessage = ArgumentCaptor.forClass(String.class);
        Mockito.verify(jmsTemplate, times(1)).convertAndSend(argumentQueue.capture(), argumentMessage.capture());
        assertEquals("queue_results", argumentQueue.getValue());
        assertEquals(message, argumentMessage.getValue());
    }

}